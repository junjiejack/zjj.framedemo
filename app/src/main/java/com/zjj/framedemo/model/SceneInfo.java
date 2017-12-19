package com.zjj.framedemo.model;

/**
 * @author zhoujunjie on 2017/12/11.
 */

public class SceneInfo {


    /**
     * h5_info : {"type":"Android","app_name":"聚保物流师","package_name":"com.jubao.logistics.agent"}
     */

    private H5InfoBean h5_info;

    public H5InfoBean getH5_info() {
        return h5_info;
    }

    public void setH5_info(H5InfoBean h5_info) {
        this.h5_info = h5_info;
    }

    public static class H5InfoBean {
        /**
         * type : Android
         * app_name : 聚保物流师
         * package_name : com.jubao.logistics.agent
         */

        private String type;
        private String app_name;
        private String package_name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        @Override
        public String toString() {
            return "H5InfoBean{" +
                    "type='" + type + '\'' +
                    ", app_name='" + app_name + '\'' +
                    ", package_name='" + package_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SceneInfo{" +
                "h5_info=" + h5_info +
                '}';
    }
}
