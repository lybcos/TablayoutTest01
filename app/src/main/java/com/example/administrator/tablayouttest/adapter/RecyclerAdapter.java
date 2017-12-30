package com.example.administrator.tablayouttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.MyApplication;
import com.example.administrator.tablayouttest.bean.AppInfo;
import com.example.administrator.tablayouttest.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private List<String>mData;
    private Context context;

    public RecyclerAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    static  class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_icon;
        FrameLayout action_progress;
        TextView action_txt,item_title,item_size,item_bottom;
        RatingBar item_ratig;
        public MyViewHolder(View view) {
            super(view);
            item_icon = (ImageView) view.findViewById(R.id.item_icon);
            action_progress = (FrameLayout) view.findViewById(R.id.action_progress);
            action_txt = (TextView) view.findViewById(R.id.action_txt);
            item_title = (TextView) view.findViewById(R.id.item_title);
            item_size = (TextView) view.findViewById(R.id.item_size);
            item_bottom = (TextView) view.findViewById(R.id.item_bottom);
            item_ratig = (RatingBar) view.findViewById(R.id.item_rating);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AppInfo info = new AppInfo();
        holder.item_title.setText("网易云课堂");
//        holder.item_size.setText((int) info.getSize());
        holder.action_txt.setText("下载");
        holder.item_icon.setImageResource(R.drawable.googleplaylogo);
        holder.item_bottom.setText("这是一个受万人喜爱的应用软件");

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
