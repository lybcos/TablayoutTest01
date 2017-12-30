package com.example.administrator.tablayouttest.fragment;


import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.shitou.googleplay.lib.randomlayout.StellarMap;
import com.shitou.googleplay.lib.randomlayout.StellarMap.Adapter;

import java.util.ArrayList;
import java.util.Random;

public class RecommentFragement extends BaseFragment {
    private StellarMap stellarMap;
    private ArrayList<String> list = new ArrayList<String>();

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);


    @Override
    protected void loadData() {

    }

    @Override
    //RecommentFragement要显示的内容写到这个方法
    protected View initView() {
        // 要显示的文字源
        list.add("优酷");
        list.add("斗地主");
        list.add("单机打麻将");
        list.add("无敌播放器");
        list.add("蓝墨云班课");
        list.add("暴走漫画");
        list.add("WIFI万能钥匙");
        list.add("火柴人联盟");
        list.add("捕鱼达人2");
        list.add("超级课程表");

        list.add("王者荣耀");
        list.add("完美校园");
        list.add("放开那三国");
        list.add("虫虫大作战");
        list.add("快手");
        list.add("今日头条");
        list.add("拼多多");
        list.add("QQ音乐");
        list.add("企鹅电竞");
        list.add("赖人听书");

        list.add("优酷");
        list.add("斗地主");
        list.add("单机打麻将");
        list.add("无敌播放器");
        list.add("蓝墨云班课");
        list.add("暴走漫画");
        list.add("WIFI万能钥匙");
        list.add("火柴人联盟");
        list.add("捕鱼达人2");
        list.add("超级课程表");

        list.add("王者荣耀");
        list.add("完美校园");
        list.add("放开那三国");
        list.add("虫虫大作战");
        list.add("快手");
        list.add("今日头条");
        list.add("拼多多");
        list.add("QQ音乐");
        list.add("企鹅电竞");
        list.add("赖人听书");




        stellarMap = new StellarMap(getContext().getApplicationContext());
        // 设置内部的TextView距离四周的内边距
        int padding = 5;     //设置控件到外边距的值
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setAdapter(new StellarMapAdapter());
        // 设置默认显示第几组的数据
        stellarMap.setGroup(0, true);// 这里默认显示第0组
        // 设置x和y方向上的显示的密度
        stellarMap.setRegularity(5, 5);// 如果值设置的过大，有可能造成子View摆放比较稀疏

        // 把fragment显示至界面,new出fragment对象
//        FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
//        fl.addView(stellarMap);


        return stellarMap;
    }

    class StellarMapAdapter implements Adapter {
        /**
         * 返回多少组数据
         */
        @Override
        public int getGroupCount() {
            return 3;
        }

        /**
         * 每组多少个数据
         */
        @Override
        public int getCount(int group) {
            return 11;
        }

        /**
         * group: 当前是第几组 position:是当前组的position
         */
        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView(getContext().getApplicationContext());
            // 根据group和组中的position计算出对应的在list中的位置
            int listPosition = group * getCount(group) + position;
            textView.setText(list.get(listPosition));

            // 1.设置随机的字体大小(随机大小)
            Random random = new Random();
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                    random.nextInt(15) + 14);// 14-29
            // 2.上色，设置随机的字体颜色
            // 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
            int red = random.nextInt(150) + 50;// 50-199
            int green = random.nextInt(150) + 50;// 50-199
            int blue = random.nextInt(150) + 50;// 50-199
            int textColor = Color.rgb(red, green, blue);// 在rgb三原色的基础上混合出一种新的颜色
            textView.setTextColor(textColor);
            return textView;
        }

        /**
         * 虽然定义了，但是并没有什么用
         */
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        /**
         * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            // 0->1->2->0
            return (group + 1) % getGroupCount();
        }

    }

}
