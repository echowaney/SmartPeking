package com.example.hashwaney.zhbj33.bean;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class NewCenterTabBean {
    public NewsCenterData data;
    public int            retcode;

    public class NewsCenterData {

        public String              countcommenturl;
        public String              more;
        public List<NewsBean>      news;
        public String              title;
        public List<NewsTopicBean> topic;
        public List<NewsTopBean>   topnews;
    }


    public class NewsTopBean {
        public boolean comment;
        public String  commentlist;
        public String  commenturl;
        public String  id;
        public String  pubdate;
        public String  title;
        public String  topimage;
        public String  type;
        public String  url;


    }

    public class NewsTopicBean {
        public String description;
        public String id;
        public String listimage;
        public String sort;
        public String title;
        public String url;


    }

    public class NewsBean {
        public boolean comment;
        public String  commentlist;
        public String  commenturl;
        public String  id;
        public String  listimage;
        public String  pubdate;
        public String  title;
        public String  type;
        public String  url;


    }


}
