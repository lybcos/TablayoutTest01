package com.example.administrator.tablayouttest.fragment;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

public class GameFragment extends BaseFragment {
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list_item, parent, false);
                hodler = new ViewHodler();
                hodler.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
                hodler.action_progress = (FrameLayout) convertView.findViewById(R.id.action_progress);
                hodler.action_txt = (TextView) convertView.findViewById(R.id.action_txt);
                hodler.item_title = (TextView) convertView.findViewById(R.id.item_title);
                hodler.item_size = (TextView) convertView.findViewById(R.id.item_size);
                hodler.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
                hodler.item_ratig = (RatingBar) convertView.findViewById(R.id.item_rating);
                convertView.setTag(hodler);
            } else {
                hodler = (ViewHodler) convertView.getTag();
            }
            hodler.item_icon.setImageResource(R.drawable.google_play);
            hodler.item_title.setText(info.getName());
//            hodler.item_size.setText((int) info.getSize());
            hodler.action_txt.setText("下载");
            hodler.item_bottom.setText(info.getDes());
            return convertView;
        }
    }

    class ViewHodler {
        ImageView item_icon;
        FrameLayout action_progress;
        TextView action_txt, item_title, item_size, item_bottom;
        RatingBar item_ratig;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
    //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        Intent intent = new Intent(getActivity(), demos[position].demoClass);
//        startActivity(intent);
//    }
    public static  class  Demoinfo{
        private final  Class <?extends Activity>demoClass;
        public Demoinfo(Class<? extends Activity> demoClass){
            this.demoClass=demoClass;
        }
//        private static  final Demoinfo[]demos={
//
//        };
    }
}
