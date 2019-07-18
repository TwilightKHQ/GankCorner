package com.gankcorner.Bean;

import java.util.List;

public class NetEaseBannerBean {

    /**
     * code : 200
     * msg : OK
     * timestamp : 1563418439714
     * data : [{"backgroundUrl":"http://p1.music.126.net/suX93Xl6XiiSJzWBdlt5Lg==/109951164221408390.jpg","picUrl":"http://p1.music.126.net/SHlCIO0C6fB-5EWi089S8w==/109951164221412632.jpg","monitorType":"","targetId":"1376873330","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1376873330"},{"backgroundUrl":"http://p1.music.126.net/4THyPA_36EEbpq8B-T5Vkg==/109951164220746822.jpg","picUrl":"http://p1.music.126.net/TE2bNoIfEjswcf0FW3uYdA==/109951164220748722.jpg","monitorType":"","targetId":"1365233944","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1365233944"},{"backgroundUrl":"http://p1.music.126.net/icrWuipfZRC2RkGFPxXi_Q==/109951164220752783.jpg","picUrl":"http://p1.music.126.net/tYA_SsKfzZi0AqO_FzM2dA==/109951164220756621.jpg","monitorType":"","targetId":"1378436698","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1378436698"},{"backgroundUrl":"http://p1.music.126.net/L-eI9guUQNupGt3RoEPtPA==/109951164220761878.jpg","picUrl":"http://p1.music.126.net/cPoy3ftjwXgMoRRwrUnsjA==/109951164220771507.jpg","monitorType":"","targetId":"1349892968","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1349892968"},{"backgroundUrl":"http://p1.music.126.net/rXIlKBIZ7_NakiJoFEa4HA==/109951164220774696.jpg","picUrl":"http://p1.music.126.net/2lTaE4NN0XnrASFVXj4PxA==/109951164220777575.jpg","monitorType":"","targetId":"1378479700","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1378479700"},{"backgroundUrl":"http://p1.music.126.net/aqLKoRVQ8u0z-R972tWFvw==/109951164221409410.jpg","picUrl":"http://p1.music.126.net/kF7LSzC_z0LJVhRvik9nfA==/109951164221413750.jpg","monitorType":"","targetId":"1374061028","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1374061028"},{"backgroundUrl":"http://p1.music.126.net/nM2U-icUqr6znql3hNrZHA==/109951164220796594.jpg","picUrl":"http://p1.music.126.net/fncZFzHMhN99Yhq6_tO4zQ==/109951164220794195.jpg","monitorType":"","targetId":"79880626","monitorImpress":"","targetType":"10","monitorClick":"","url":"/album?id=79880626"},{"backgroundUrl":"http://p1.music.126.net/nhC0YTjoZlZ5Znjttx-itg==/109951164220785717.jpg","picUrl":"http://p1.music.126.net/lwWuiZpRBNKcTsNWwOTxDg==/109951164220779452.jpg","monitorType":"","targetId":"1378463244","monitorImpress":"","targetType":"1","monitorClick":"","url":"/song?id=1378463244"},{"backgroundUrl":"http://p1.music.126.net/sEHFufhqqYkkuO0HaU9EiQ==/109951164219958633.jpg","picUrl":"http://p1.music.126.net/vyVUR6VCIk46nyPGBIn7Kw==/109951164219949952.jpg","monitorType":"","targetId":"80418297","monitorImpress":"","targetType":"10","monitorClick":"","url":"/album?id=80418297"}]
     */

    private int code;
    private String msg;
    private long timestamp;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * backgroundUrl : http://p1.music.126.net/suX93Xl6XiiSJzWBdlt5Lg==/109951164221408390.jpg
         * picUrl : http://p1.music.126.net/SHlCIO0C6fB-5EWi089S8w==/109951164221412632.jpg
         * monitorType :
         * targetId : 1376873330
         * monitorImpress :
         * targetType : 1
         * monitorClick :
         * url : /song?id=1376873330
         */

        private String backgroundUrl;
        private String picUrl;
        private String monitorType;
        private String targetId;
        private String monitorImpress;
        private String targetType;
        private String monitorClick;
        private String url;

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getMonitorImpress() {
            return monitorImpress;
        }

        public void setMonitorImpress(String monitorImpress) {
            this.monitorImpress = monitorImpress;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getMonitorClick() {
            return monitorClick;
        }

        public void setMonitorClick(String monitorClick) {
            this.monitorClick = monitorClick;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
