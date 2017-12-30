package com.example.administrator.tablayouttest.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SubjectFragment extends BaseFragment {
    listAdapter adapter;
    private List<String> mlist;

    @Override
    protected void loadData() {
        Toast.makeText(mContent,"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }

    protected View initView() {
        ListView listView = new ListView(getContext());
        mlist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mlist.add("这是");
        }
        adapter = new listAdapter(mlist);
        listView.setAdapter(adapter);
        return listView;
    }

    class listAdapter extends BaseAdapter {
            private List<String> mlist;


            public listAdapter(List<String> mlist) {
                this.mlist = mlist;
            }


            public int getCount() {
                return mlist.size();
            }


            public Object getItem(int position) {
                return mlist.get(position);
            }


            public long getItemId(int position) {
                return position;
            }


            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHodler hodler;
                AppInfo info = new AppInfo();
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_list_item, parent, false);
                    hodler = new ViewHodler();
                    hodler.image = (ImageView) convertView.findViewById(R.id.image);
                    hodler.tv_main = (TextView) convertView.findViewById(R.id.tv_main);
                    convertView.setTag(hodler);
                } else {
                    hodler = (ViewHodler) convertView.getTag();
                }
                hodler.image.setImageResource(R.drawable.google_play);
                hodler.tv_main.setText("文艺青年VS2B");
                return convertView;
            }
        }

        class ViewHodler {
            ImageView image;
            TextView tv_main;

        }
}
