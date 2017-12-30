package com.example.administrator.tablayouttest.activity;

import android.content.Context;
import android.view.View;

import com.example.administrator.tablayouttest.R;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by Administrator on 2017/11/6.
 */

public class DefineBAGRefreshWithLoadView extends BGARefreshViewHolder {
    protected BGARefreshLayout mRefreshLayout;
    /**
     * 下拉刷新控件
     */
    protected View mRefreshHeaderView;
    /**
     * 上拉加载更多控件
     */
    protected Context mContext;
    protected View mLoadMoreFooterView;
    /**
     * 是否开启加载更多功能
     */
    private boolean mIsLoadingMoreEnabled = true;
    /**
     * @param context
     * @param isLoadingMoreEnabled 上拉加载更多是否可用
     */
    public DefineBAGRefreshWithLoadView(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
        this.mContext=context;
        this.mIsLoadingMoreEnabled=isLoadingMoreEnabled;

    }
    /**
     * 获取头部下拉刷新控件
     *
     * @return
     */

    @Override
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(mContext, R.layout.header_bga_dodo, null);
        }
        return null;
    }
    /**
     * 下拉刷新控件可见时，处理上下拉进度
     *
     * @param scale         下拉过程0 到 1，回弹过程1 到 0，没有加上弹簧距离移动时的比例
     * @param moveYDistance 整个下拉刷新控件paddingTop变化的值，如果有弹簧距离，会大于整个下拉刷新控件的高度
     */
    @Override
    public void handleScale(float scale, int moveYDistance) {

    }
    //进入到未处理下拉刷新状态
    @Override
    public void changeToIdle() {

    }

    //开始下拉
    @Override
    public void changeToPullDown() {

    }
    //下拉到一定程度，可以刷新
    @Override
    public void changeToReleaseRefresh() {

    }
    //已经开始刷新
    @Override
    public void changeToRefreshing() {

    }
    //结束刷新
    @Override
    public void onEndRefreshing() {

    }
}
