package com.example.hashwaney.zhbj33.bean;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/30.
 */

public class NewsCenterGroupViewBean {

    public NewsCenterGroupDataBean data;
    public int                     retcode;

    public class NewsCenterGroupDataBean {
        public String                        countcommenturl;
        public String                        more;
        public List<NewsCenterGroupNewsBean> news;
        public String                        title;
        public List                          topic;

    }

    public class NewsCenterGroupNewsBean {
        public boolean comment;
        public String  commentlist;
        public String  commenturl;
        public String  id;
        public String  largeimage;
        public String  listimage;
        public String  pubdate;
        public String  smallimage;
        public String  title;
        public String  type;
        public String  url;

    }


}
