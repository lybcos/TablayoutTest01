package com.example.administrator.tablayouttest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.adapter.RecyclerAdapter;
import com.example.administrator.tablayouttest.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class SearchActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private EditText mEtSearch;
    private Button mBtSearch;
    private String keyword = null;
    private BGARefreshLayout mSearchBgaRefresh;
    private RecyclerView mRvContent;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    /** 设置一共请求多少次数据 */
//    private TextView mTitle;
    /** 数据 */
    private int ALLSUM = 0;
    private Context mContext;
    private static int THRESHOLD_OFFSET = 10;
//    private TextView mTitle;
    /** 数据 */
    private List<String> mListData = new ArrayList<String>();
    /** 一次加载数据的条数 */
    private int DATASIZE = 10;
    private List<String>mlist = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mListData.clear();
                    setData();
                    mSearchBgaRefresh.endRefreshing();
                    break;
                case 1:
                    setData();
                    mSearchBgaRefresh.endLoadingMore();
                default:
                    break;
            }
        }
    };
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext=this;
        initView();
        setBgaRefreshLayout();
        setRecyclerView();
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
//        initRvContent();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
//        initRefresh();
        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!mEtSearch.getText().toString().equals("")) {
                        mSearchBgaRefresh.beginRefreshing();
                    }
            }
        });

  mRvContent.setOnScrollListener(new RecyclerView.OnScrollListener() {
        boolean controlVisible = true;
        int scrollDistance = 0;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (controlVisible&&scrollDistance>THRESHOLD_OFFSET){
                animationHide();
                controlVisible = false;
                scrollDistance = 0;
            } else if (!controlVisible && scrollDistance < -THRESHOLD_OFFSET){//手指下滑即Scroll向上滚动的时候，dy为负
                animationShow();
                controlVisible = true;
                scrollDistance = 0;
            }
            //当scrollDistance累计到隐藏（显示)ToolBar之后，如果Scroll向下（向上）滚动，则停止对scrollDistance的累加
            //直到Scroll开始往反方向滚动，再次启动scrollDistance的累加
            if ((controlVisible && dy > 0) || (!controlVisible && dy < 0)){
                scrollDistance += dy;
            }
        }
    private void animationShow() {
        toolbar.animate().setInterpolator(new AccelerateInterpolator(1))
                .setDuration(180)
                .translationY(0);
    }

    private void animationHide() {
        toolbar.animate()
                .translationY(-toolbar.getBottom())
                .setInterpolator(new LinearInterpolator())
                .setDuration(180);
    }
        });
        }
    private void setRecyclerView() {
        mRvContent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void setBgaRefreshLayout() {
        mSearchBgaRefresh.setRefreshViewHolder(new DefineBAGRefreshView(this,true,true));
    }

    private void initView() {
//        mTitle = (TextView) findViewById(R.id.fml_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mBtSearch = (Button) findViewById(R.id.bt_search);
        mSearchBgaRefresh = (BGARefreshLayout) findViewById(R.id.search_bga_refresh);
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mSearchBgaRefresh.setDelegate(this);
    }
    private void setRecyclerCommadapter() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mListData);
        mRvContent.setAdapter(mRecyclerViewAdapter);
    }
    public void setData() {
        for (int i=0;i<DATASIZE;i++) {
            mListData.add("第" + (ALLSUM * 10 + i) + "条数据");
        }
        if (ALLSUM == 0) {
            setRecyclerCommadapter();
        } else {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (upIntent != null) {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this,upIntent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 刷新 */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ALLSUM = 0;
        handler.sendEmptyMessageDelayed(0 , 2000);
    }
    /** 加载 */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if(ALLSUM == 2){
            Toast.makeText(mContext , "没有更多数据" , Toast.LENGTH_SHORT).show();
            return false;
        }
        ALLSUM++;
        handler.sendEmptyMessageDelayed(1 , 2000);
        return true;
    }
}
