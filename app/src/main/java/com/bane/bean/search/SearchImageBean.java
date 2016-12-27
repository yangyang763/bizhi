package com.bane.bean.search;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class SearchImageBean {


    /**
     * http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=hot&location=1
     *
     * keyword : 汽车
     * imgs : ["http://bzpic.spriteapp.cn/picture2/M00/0C/39/wKiFWVRsDzGAeSvwAAFtGFI8Ifg950.jpg"]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String keyword;
        private List<String> imgs;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "keyword='" + keyword + '\'' +
                    ", imgs=" + imgs +
                    '}';
        }
    }
}
