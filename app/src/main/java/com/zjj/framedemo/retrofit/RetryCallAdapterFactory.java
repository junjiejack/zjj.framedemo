package com.zjj.framedemo.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public class RetryCallAdapterFactory extends CallAdapter.Factory {

    private RxJavaCallAdapterFactory originCallAdaptorFactory;

    private RetryCallAdapterFactory() {
        originCallAdaptorFactory = RxJavaCallAdapterFactory.create();
    }

    public static RetryCallAdapterFactory create() {
        return new RetryCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        final CallAdapter<?> ca = originCallAdaptorFactory.get(returnType, annotations, retrofit);
        return new CallAdapter<Observable<?>>() {
            @Override public Type responseType() {
                return ca.responseType();
            }

            int restRetryCount = 3;

            @Override public <R> Observable<?> adapt(Call<R> call) {
                Observable<?> rx = (Observable<?>) ca.adapt(call);
                return rx.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override public Observable<?> call(Observable<? extends Throwable> errors) {
                        return errors.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override public Observable<?> call(Throwable error) {
                                boolean needRetry = false;
                                if (restRetryCount >= 1) {
                                    if (error instanceof IOException) {
                                        needRetry = true;
                                    } else if (error instanceof HttpException) {
                                        if (((HttpException) error).code() != 200 || ((HttpException) error).code() != 401 || ((HttpException) error).code() != 400) {
                                            needRetry = true;
                                        }
                                    }
                                }
                                if (needRetry) {
                                    restRetryCount--;
                                    return Observable.timer(1, TimeUnit.SECONDS);
                                } else {
                                    return Observable.error(error);
                                }
                            }
                        });
                    }
                });
            }
        };
    }
}

