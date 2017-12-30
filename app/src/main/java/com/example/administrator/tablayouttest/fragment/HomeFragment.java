package com.example.administrator.tablayouttest.fragment;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.DefineBAGRefreshView;
import com.example.administrator.tablayouttest.activity.MyApplication;
import com.example.administrator.tablayouttest.adapter.RecyclerAdapter;
import com.example.administrator.tablayouttest.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private List<String>mlist;
    private Myadapter adapter01;
    private int mPreviousPos;
    private TextView tv_title;
    private ViewPager viewPager;
    private LinearLayout llContainer;
    private RecyclerAdapter adapter;
    private BGARefreshLayout mSearchBgaRefresh;
    private Handler mhandler;
    private RecyclerView mRecyclerView;
    private int DATASIZE = 10;
    private int ALLSUM = 0;
    private int[] imageId = new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};
    private final String[] mImageDes = { "双11福利大放送", "从零基础到框架",
            "简七理财", "美的PPT，本来就该这样" };

    @Override
    protected void loadData() {

    }

    @Override
    protected View initView() {
       View view= LayoutInflater.from(getContext()).inflate(R.layout.homefragment, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        setData();
        viewPager = (ViewPager) view.findViewById(R.id.vp_pager01);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        mSearchBgaRefresh = (BGARefreshLayout) view.findViewById(R.id._home_bga_refresh);
        mSearchBgaRefresh.setDelegate(this);
//        initRefresh();
        setRecyclerView();
        setBgaRefreshLayout();
        llContainer = (LinearLayout)view. findViewById(R.id.ll_container);
        adapter01 = new Myadapter();
        viewPager.setAdapter(adapter01);
        mhandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        int currentnow = viewPager.getCurrentItem();// 获取当前页面位置
                        viewPager.setCurrentItem(++currentnow);// 跳到下一个页面
                        // 继续发送延时2秒的消息, 形成类似递归的效果, 使广告一直循环切换
                        mhandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case 1:
                    mlist.clear();
                     setData();
                        mSearchBgaRefresh.endRefreshing();
                        break;
                    case 2:
                        setData();
                        mSearchBgaRefresh.endLoadingMore();
                        break;
                    default:
                        break;
                }
            }
        };
        viewPager.setCurrentItem(imageId.length*10000);//这句话意思就是设置好当前页面是哪个，如果是第一个的话，
        // 有些时候我们滑动到第四，五....然后突然又向左滑动，滑到0就划不动了，就没有那种无限循环的效果了。
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//给ViewPager设置监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos = position % mImageDes.length;
                tv_title.setText(mImageDes[pos]);// 更新新闻标题

                // 更新小圆点
                llContainer.getChildAt(pos).setEnabled(true);// 将选中的页面的圆点设置为红色
                // 将上一个圆点变为灰色
                llContainer.getChildAt(mPreviousPos).setEnabled(false);

                // 更新上一个页面位置
                mPreviousPos = pos;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置触摸事件
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下动作
                        mhandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP://手势抬起动作
                        mhandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                }
                return false;
            }
        });
        mhandler.sendEmptyMessageDelayed(0, 2000);
        tv_title.setText(mImageDes[0]);
        for (int i=0;i<imageId.length;i++) {
            ImageView view01 = new ImageView(getContext());
            view01.setBackgroundResource(R.drawable.shape_point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0) {// 从第2个圆点开始设置左边距, 保证圆点之间的间距
                params.leftMargin = 6;
                view01.setPressed(false);// 设置不可用, 变为灰色圆点
            }

            view01.setLayoutParams(params);

            llContainer.addView(view01);
        }

        return view;
    }

    private void setData() {
        mlist = new ArrayList<>();
        for (int i=0;i<DATASIZE;i++) {
            mlist.add("第" + (ALLSUM * 10 + i) + "条数据");
        }
        if (ALLSUM == 0) {
            setRecyclerCommadapter();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void setRecyclerCommadapter() {
        adapter = new RecyclerAdapter(mlist,MyApplication.getContext());
        mRecyclerView.setAdapter(adapter);

    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void setBgaRefreshLayout() {
        mSearchBgaRefresh.setRefreshViewHolder(new DefineBAGRefreshView(MyApplication.getContext(),true,true));
    }
//    private void initRefresh() {
//
//        BGANormalRefreshViewHolder viewHolder = new BGANormalRefreshViewHolder(MyApplication.getContext(),true);
//        viewHolder.setLoadingMoreText("加载更多");
//        viewHolder.setLoadMoreBackgroundColorRes(R.color.white);
//        viewHolder.setRefreshViewBackgroundColorRes(R.color.white);
//        mSearchBgaRefresh.setRefreshViewHolder(viewHolder);
//    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ALLSUM=0;
        mhandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (ALLSUM == 2) {
            Toast.makeText(getContext(), "没有更多的数据了", Toast.LENGTH_LONG).show();
            return false;
        }
            ALLSUM++;
            mhandler.sendEmptyMessageDelayed(2, 2000);
             return false;
    }
//以下是轮播图的适配器
    class Myadapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        // 类似getView方法, 初始化每个item的布局, viewpager默认自动加载前一张和后一张图片, 保证始终保持3张图片,
        // 剩余的都需要销毁
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int pos=position%imageId.length;
            ImageView view = new ImageView(getContext());
            view.setBackgroundResource(imageId[pos]);
            container.addView(view);
            return view;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            int pos=position%imageId.length;
//            return mlistk.get(pos);
//        }
    }
//    class listAdapter extends BaseAdapter {
//        private List<String>mlist;
//
//
//        public listAdapter( List<String> mlist) {
//            this.mlist = mlist;
//        }
//
//
//        public int getCount() {
//            return mlist.size();
//        }
//
//
//        public Object getItem(int position) {
//            return mlist.get(position);
//        }
//
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHodler hodler;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//                hodler = new ViewHodler();
//                hodler.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
//                hodler.action_progress = (FrameLayout) convertView.findViewById(R.id.action_progress);
//                hodler.action_txt = (TextView) convertView.findViewById(R.id.action_txt);
//                hodler.item_title = (TextView) convertView.findViewById(R.id.item_title);
//                hodler.item_size = (TextView) convertView.findViewById(R.id.item_size);
//                hodler.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
//                hodler.item_ratig = (RatingBar) convertView.findViewById(R.id.item_rating);
//                convertView.setTag(hodler);
//            }else {
//                hodler = (ViewHodler) convertView.getTag();
//            }
//            hodler.item_icon.setImageResource(R.drawable.google_play);
//            hodler.item_title.setText("网易云课堂");
//            hodler.item_size.setText("4.57MB");
//            hodler.action_txt.setText("下载");
//            hodler.item_bottom.setText("这是一款受千万人宠爱的应用app");
//            return convertView;
//        }
//    }
//    class ViewHodler{
//        ImageView item_icon;
//        FrameLayout action_progress;
//        TextView action_txt,item_title,item_size,item_bottom;
//        RatingBar item_ratig;
//
//    }
}
