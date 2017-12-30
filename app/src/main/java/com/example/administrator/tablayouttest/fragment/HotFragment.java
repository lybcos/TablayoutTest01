package com.example.administrator.tablayouttest.fragment;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Administrator on 2017/10/30.
 */

public class HotFragment extends BaseFragment {
    @Override
    protected void loadData() {
        Toast.makeText(mContent,"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }


    protected View initView() {
        TextView mView = new TextView(mContent);
        mView.setText("Fragment头条");
        mView.setGravity(Gravity.CENTER);
        mView.setTextSize(18);
        mView.setTextColor(Color.BLACK);
        return mView;
    }
}
