package com.zjj.framedemo.model;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public class LoginResult {

    /**
     * data : {"access_token":"52|8iVttKCi4DQOGvD7D3lY0bSu1RpXkY9u93co0pUONN27BUnZHjlm+TjR6awQPvE3oksyhT+eOx3fK8mYLbu5lw==","role_id":2048}
     * err_code : 0
     */

    private DataBean data;
    private int err_code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public static class DataBean {
        /**
         * access_token : 52|8iVttKCi4DQOGvD7D3lY0bSu1RpXkY9u93co0pUONN27BUnZHjlm+TjR6awQPvE3oksyhT+eOx3fK8mYLbu5lw==
         * role_id : 2048
         */

        private String access_token;
        private int role_id;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }
    }
}
