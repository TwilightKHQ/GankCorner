package com.gankcorner.Bean;

import java.util.List;

public class OneBean {
    /**
     * code : 200
     * msg : OK
     * timestamp : 1563410622560
     * data : {"content_bgcolor":"","ad_type":0,"start_video":"","has_reading":0,"serial_list":[],"title":"摄影","pic_info":"Jordan Steranka","number":0,"video_url":"","content_type":"0","share_list":{"qq":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=qq","audio":"","title":"","desc":""},"wx":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=singlemessage","audio":"","title":"","desc":""},"weibo":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=weibo","audio":"","title":"ONE一个 想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴\u2014\u2014张小娴 下载ONE一个APP:http://weibo.com/p/100404157874","desc":""},"wx_timeline":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=timeline","audio":"","title":"","desc":""}},"tag_list":[],"serial_id":0,"id":"18502","ad_closetime":"","last_update_date":"2019-07-12 11:27:34","like_count":1602,"item_id":"2511","ad_pvurl_vendor":"","content_id":"2511","forward":"想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。","author":{},"share_info":{"image":"http://image.wufazhuce.com/Fs7JJaPxG1XNFD8MzaPSZmRqGZIK","title":"VOL.2476","url":"http://m.wufazhuce.com/one/2511","content":"想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴"},"words_info":"张小娴","audio_platform":2,"volume":"VOL.2476","ad_id":0,"ad_pvurl":"","ad_share_cnt":"","ad_linkurl":"","img_url":"http://image.wufazhuce.com/Fs7JJaPxG1XNFD8MzaPSZmRqGZIK","post_date":"2019-07-18 06:00:00","share_url":"http://m.wufazhuce.com/one/2511","subtitle":"","audio_url":"","movie_story_id":0,"category":"0","display_category":"4","ad_makettime":""}
     */

    private int code;
    private String msg;
    private long timestamp;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content_bgcolor :
         * ad_type : 0
         * start_video :
         * has_reading : 0
         * serial_list : []
         * title : 摄影
         * pic_info : Jordan Steranka
         * number : 0
         * video_url :
         * content_type : 0
         * share_list : {"qq":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=qq","audio":"","title":"","desc":""},"wx":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=singlemessage","audio":"","title":"","desc":""},"weibo":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=weibo","audio":"","title":"ONE一个 想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴\u2014\u2014张小娴 下载ONE一个APP:http://weibo.com/p/100404157874","desc":""},"wx_timeline":{"imgUrl":"","link":"http://m.wufazhuce.com/one/2511?channel=timeline","audio":"","title":"","desc":""}}
         * tag_list : []
         * serial_id : 0
         * id : 18502
         * ad_closetime :
         * last_update_date : 2019-07-12 11:27:34
         * like_count : 1602
         * item_id : 2511
         * ad_pvurl_vendor :
         * content_id : 2511
         * forward : 想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。
         * author : {}
         * share_info : {"image":"http://image.wufazhuce.com/Fs7JJaPxG1XNFD8MzaPSZmRqGZIK","title":"VOL.2476","url":"http://m.wufazhuce.com/one/2511","content":"想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴"}
         * words_info : 张小娴
         * audio_platform : 2
         * volume : VOL.2476
         * ad_id : 0
         * ad_pvurl :
         * ad_share_cnt :
         * ad_linkurl :
         * img_url : http://image.wufazhuce.com/Fs7JJaPxG1XNFD8MzaPSZmRqGZIK
         * post_date : 2019-07-18 06:00:00
         * share_url : http://m.wufazhuce.com/one/2511
         * subtitle :
         * audio_url :
         * movie_story_id : 0
         * category : 0
         * display_category : 4
         * ad_makettime :
         */

        private String content_bgcolor;
        private int ad_type;
        private String start_video;
        private int has_reading;
        private String title;
        private String pic_info;
        private int number;
        private String video_url;
        private String content_type;
        private ShareListBean share_list;
        private int serial_id;
        private String id;
        private String ad_closetime;
        private String last_update_date;
        private int like_count;
        private String item_id;
        private String ad_pvurl_vendor;
        private String content_id;
        private String forward;
        private AuthorBean author;
        private ShareInfoBean share_info;
        private String words_info;
        private int audio_platform;
        private String volume;
        private int ad_id;
        private String ad_pvurl;
        private String ad_share_cnt;
        private String ad_linkurl;
        private String img_url;
        private String post_date;
        private String share_url;
        private String subtitle;
        private String audio_url;
        private int movie_story_id;
        private String category;
        private String display_category;
        private String ad_makettime;
        private List<?> serial_list;
        private List<?> tag_list;

        public String getContent_bgcolor() {
            return content_bgcolor;
        }

        public void setContent_bgcolor(String content_bgcolor) {
            this.content_bgcolor = content_bgcolor;
        }

        public int getAd_type() {
            return ad_type;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public String getStart_video() {
            return start_video;
        }

        public void setStart_video(String start_video) {
            this.start_video = start_video;
        }

        public int getHas_reading() {
            return has_reading;
        }

        public void setHas_reading(int has_reading) {
            this.has_reading = has_reading;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_info() {
            return pic_info;
        }

        public void setPic_info(String pic_info) {
            this.pic_info = pic_info;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public ShareListBean getShare_list() {
            return share_list;
        }

        public void setShare_list(ShareListBean share_list) {
            this.share_list = share_list;
        }

        public int getSerial_id() {
            return serial_id;
        }

        public void setSerial_id(int serial_id) {
            this.serial_id = serial_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAd_closetime() {
            return ad_closetime;
        }

        public void setAd_closetime(String ad_closetime) {
            this.ad_closetime = ad_closetime;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getAd_pvurl_vendor() {
            return ad_pvurl_vendor;
        }

        public void setAd_pvurl_vendor(String ad_pvurl_vendor) {
            this.ad_pvurl_vendor = ad_pvurl_vendor;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public ShareInfoBean getShare_info() {
            return share_info;
        }

        public void setShare_info(ShareInfoBean share_info) {
            this.share_info = share_info;
        }

        public String getWords_info() {
            return words_info;
        }

        public void setWords_info(String words_info) {
            this.words_info = words_info;
        }

        public int getAudio_platform() {
            return audio_platform;
        }

        public void setAudio_platform(int audio_platform) {
            this.audio_platform = audio_platform;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public int getAd_id() {
            return ad_id;
        }

        public void setAd_id(int ad_id) {
            this.ad_id = ad_id;
        }

        public String getAd_pvurl() {
            return ad_pvurl;
        }

        public void setAd_pvurl(String ad_pvurl) {
            this.ad_pvurl = ad_pvurl;
        }

        public String getAd_share_cnt() {
            return ad_share_cnt;
        }

        public void setAd_share_cnt(String ad_share_cnt) {
            this.ad_share_cnt = ad_share_cnt;
        }

        public String getAd_linkurl() {
            return ad_linkurl;
        }

        public void setAd_linkurl(String ad_linkurl) {
            this.ad_linkurl = ad_linkurl;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getPost_date() {
            return post_date;
        }

        public void setPost_date(String post_date) {
            this.post_date = post_date;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public int getMovie_story_id() {
            return movie_story_id;
        }

        public void setMovie_story_id(int movie_story_id) {
            this.movie_story_id = movie_story_id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDisplay_category() {
            return display_category;
        }

        public void setDisplay_category(String display_category) {
            this.display_category = display_category;
        }

        public String getAd_makettime() {
            return ad_makettime;
        }

        public void setAd_makettime(String ad_makettime) {
            this.ad_makettime = ad_makettime;
        }

        public List<?> getSerial_list() {
            return serial_list;
        }

        public void setSerial_list(List<?> serial_list) {
            this.serial_list = serial_list;
        }

        public List<?> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<?> tag_list) {
            this.tag_list = tag_list;
        }

        public static class ShareListBean {
            public static class QqBean {
                /**
                 * imgUrl :
                 * link : http://m.wufazhuce.com/one/2511?channel=qq
                 * audio :
                 * title :
                 * desc :
                 */

                private String imgUrl;
                private String link;
                private String audio;
                private String title;
                private String desc;

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class WxBean {
                /**
                 * imgUrl :
                 * link : http://m.wufazhuce.com/one/2511?channel=singlemessage
                 * audio :
                 * title :
                 * desc :
                 */

                private String imgUrl;
                private String link;
                private String audio;
                private String title;
                private String desc;

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class WeiboBean {
                /**
                 * imgUrl :
                 * link : http://m.wufazhuce.com/one/2511?channel=weibo
                 * audio :
                 * title : ONE一个 想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴——张小娴 下载ONE一个APP:http://weibo.com/p/100404157874
                 * desc :
                 */

                private String imgUrl;
                private String link;
                private String audio;
                private String title;
                private String desc;

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class WxTimelineBean {
            }
        }

        public static class AuthorBean {
        }

        public static class ShareInfoBean {
            /**
             * image : http://image.wufazhuce.com/Fs7JJaPxG1XNFD8MzaPSZmRqGZIK
             * title : VOL.2476
             * url : http://m.wufazhuce.com/one/2511
             * content : 想要忘记一段感情，方法永远只有一个：时间和新欢。要是时间和新欢也不能让你忘记一段感情，原因只有一个：时间不够长，新欢不够好。 by 张小娴
             */

            private String image;
            private String title;
            private String url;
            private String content;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
