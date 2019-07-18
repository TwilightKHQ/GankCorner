package com.gankcorner.Bean;

import java.util.List;

public class GankArticleBean {
//    private String who;
//    private String url;
//
//    public GankArticleBean(String name, String url) {
//        this.who = name;
//        this.url = url;
//    }
//
//    public String getWho() {
//        return who;
//    }
//
//    public String getName() {
//        return url;
//    }

    /**
     * error : false
     * results : [{"_id":"5ce52e419d212239d75618af","createdAt":"2019-05-22T11:10:57.722Z","desc":"基于 Kotlin+模块化+响应式+MVVM 实现的风格简约、代码优雅的WanAndroid客户端","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc66v192j30u01hc4b7","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc68iurdj30u01hcti9","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc69igm5j30u01hc763","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc6ah5exj30u01hc0ww"],"publishedAt":"2019-05-23T01:34:42.491Z","source":"web","type":"Android","url":"https://github.com/ITGungnir/KotlinWanAndroid","used":true,"who":"潇湘剑雨"},{"_id":"5ce26b2f9d212239d75618a6","createdAt":"2019-05-20T08:54:07.624Z","desc":"爱奇艺开源的适用于Android APP的崩溃报告库.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1g37vyvsprwj30y20u0tfe"],"publishedAt":"2019-05-20T08:54:33.611Z","source":"web","type":"Android","url":"https://github.com/iqiyi/xCrash","used":true,"who":"lijinshanmx"},{"_id":"5cdd516d9d212239df927ab3","createdAt":"2019-05-16T12:02:53.811Z","desc":"自定义进度条，包括圆环型百分比进度条，直线型百分比进度条，还有仿360杀毒百分比进度条。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1g37vyr2tsaj308c0go3z0","https://ww1.sinaimg.cn/large/0073sXn7gy1g37vyrenb5j308c0go3yr","https://ww1.sinaimg.cn/large/0073sXn7gy1g37vyu8ohug310w0q24qp","https://ww1.sinaimg.cn/large/0073sXn7gy1g37vyuvf7dj308c0go3ys"],"publishedAt":"2019-05-19T13:42:49.463Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCProgress","used":true,"who":"潇湘剑雨"},{"_id":"5cde10499d212239dd3b8fd7","createdAt":"2019-05-17T01:37:13.143Z","desc":"便捷使用Android中LiveData的相关功能","publishedAt":"2019-05-19T13:41:52.290Z","source":"web","type":"Android","url":"https://github.com/simplezhli/Saber","used":true,"who":"潇湘剑雨"},{"_id":"5cdf8c8f9d212239da9c6acd","createdAt":"2019-05-18T04:39:43.50Z","desc":"我是如何做到 GitHub star 在 5 天内从 0 飙至 666 的","publishedAt":"2019-05-18T09:19:08.885Z","source":"web","type":"Android","url":"https://juejin.im/post/5cde5d7a51882525e968cdcd","used":true,"who":"潇湘剑雨"},{"_id":"5cda2aad9d212239df927aab","createdAt":"2019-05-14T02:40:45.979Z","desc":"Android 史上最优雅的实现文件上传、下载及进度的监听","publishedAt":"2019-05-15T02:06:54.229Z","source":"web","type":"Android","url":"https://github.com/liujingxing/RxHttp","used":true,"who":"潇湘剑雨"},{"_id":"5cd159a99d212239df927a9d","createdAt":"2019-05-07T10:10:49.364Z","desc":"RecyclerView问题汇总","publishedAt":"2019-05-10T01:27:00.593Z","source":"web","type":"Android","url":"https://juejin.im/post/5cce410551882541e40e471d","used":true,"who":"潇湘剑雨"},{"_id":"5cd282309d212239da9c6ab6","createdAt":"2019-05-08T07:16:00.722Z","desc":"2019 年Google I/O 大会 你需要知道的一切","publishedAt":"2019-05-08T13:10:02.144Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/w1h9cw_qWZ7SznBQSKw40A","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<GankArticleBean.ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankArticleBean.ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<GankArticleBean.ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5ce52e419d212239d75618af
         * createdAt : 2019-05-22T11:10:57.722Z
         * desc : 基于 Kotlin+模块化+响应式+MVVM 实现的风格简约、代码优雅的WanAndroid客户端
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc66v192j30u01hc4b7","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc68iurdj30u01hcti9","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc69igm5j30u01hc763","https://ww1.sinaimg.cn/large/0073sXn7gy1g3dc6ah5exj30u01hc0ww"]
         * publishedAt : 2019-05-23T01:34:42.491Z
         * source : web
         * type : Android
         * url : https://github.com/ITGungnir/KotlinWanAndroid
         * used : true
         * who : 潇湘剑雨
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
