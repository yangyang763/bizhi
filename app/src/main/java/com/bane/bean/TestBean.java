package com.bane.bean;

import android.widget.ImageView;

//精选壁纸接口
//        主界面
//        推荐
//        最新
//        (点击进入详细界面展示大图就可以了)
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=0
//        热门
//        (点击进入详细界面展示大图就可以了)
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid=0
//        随机
//        (点击进入详细界面展示大图就可以了)
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=0
//        分类
//        主列表界面
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=category
//        美女图片界面
//        最新
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=1042
//        热门
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid=1042
//        随机
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=1042
//        创意设计界面
//        最新
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=1056
//        热门
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid=1056
//        随机
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=1056
//        后徐界面的规律,
//        1.参考上述美女图片界面,创意设计界面(最后url的id不同就可以获取不同的界面)
//        2.url中的关键字:wallPaperNew(最新),hotRecent(热门),random(随机)
//        3.url末尾的数字:比如1042,1056记录在主列表中的字段[SecondCategoryList中]
//        搜索
//        热门搜索
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=hot&location=1
//        查看更多
//        (就是内部专题接口中的前五张图片)
//        专题列表
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=topic&a=list&topictype=2&size=10
//        查看更多下方主列表
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=hot&location=3
//        页面上方搜索功能
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=search&q=MyWordKey&p=MyPager&s=30
//        其中 MyPager 代表当前读取页数
//        MyWordKey代表当前搜索关键字
//        ViewPager中的item被点击后
//        http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=topic&a=detail&index=MyPager&size=9&typeid=2&topicid=MyID
//        其中 MyPager 代表当前读取页数
//        MyID 代表被点击的item对应的ID值

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class TestBean {

    private String name;
    private String introduce;
    private int color;
    private int pic;




    public TestBean(String name, String introduce, int color, int pic) {
        this.name = name;
        this.introduce = introduce;
        this.color = color;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public int getColor() {
        return color;
    }

    public int getPic() {
        return pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", color=" + color +
                ", pic=" + pic +
                '}';
    }
}
