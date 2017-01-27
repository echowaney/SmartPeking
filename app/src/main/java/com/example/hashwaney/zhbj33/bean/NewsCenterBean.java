package com.example.hashwaney.zhbj33.bean;

import java.util.List;


/**
 * Created by HashWaney on 2017/1/27.
 */

public class NewsCenterBean {


    public List<NewsMenuBean> data;
    public List<String>       extend;
    public int                retcode;


    //是菜单选项的bean
   public class NewsMenuBean {

        public List<NewsTabBean> children;
        public int               id;
        public String            title;
        public int               type;
        public String            url;
        public String            dayurl;
        public String            excurl;
        public String            weekuel;
    }

    //新闻tab的bean :北京....
    public class NewsTabBean {
        public int    id;
        public String title;
        public int    type;
        public String url;

    }


}
