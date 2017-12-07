package com.rambler.util;

/**
 * Created by maocheng on 2017/1/8.
 */
public interface PermissionsResultListener {
    void onPermissionGranted();

    void onPermissionDenied();
}
