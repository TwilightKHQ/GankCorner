package com.gankcorner.Bean;

public class WanArticle {
        private String author;
        private String chapterName;
        private String link;
        private String niceDate;
        private String superChapterName;
        private String title;

        public WanArticle(String author, String chapterName, String link, String niceDate,
                          String superChapterName, String title) {
            this.author = author;
            this.chapterName = chapterName;
            this.link = link;
            this.niceDate = niceDate;
            this.superChapterName = superChapterName;
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public String getChapterName() {
            return chapterName;
        }

        public String getLink() {
            return link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public String getTitle() {
            return title;
        }
}
