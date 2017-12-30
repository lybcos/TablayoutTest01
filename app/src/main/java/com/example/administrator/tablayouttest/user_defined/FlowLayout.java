package com.example.administrator.tablayouttest.user_defined;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

//import com.example.administrator.tablayouttest.fragment.RecommentFragement;

/**
 * Created by Administrator on 2017/11/1.
 */

public class FlowLayout extends ViewGroup {
    private float mVerticalSpacing;
    private float mHorizontalSpacing;
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHorizontalSpacing(float pixelsize) {
        mHorizontalSpacing=pixelsize;
    }
    public void setVerticalSpacing(float pixelsize) {
        mVerticalSpacing=pixelsize;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int selfWidth = resolveSize(0, widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop=getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom=getPaddingBottom();

        int childLeft=paddingLeft;
        int childTop=paddingTop;
        int lineHeight=0;

        //通过计算每一个子控件的高度，得到自己的高度
        for (int i=0,childCount=getChildCount();i<childCount;++i){
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            childView.measure(getChildMeasureSpec(widthMeasureSpec,paddingLeft+paddingRight,
                    childLayoutParams.width),getChildMeasureSpec(heightMeasureSpec,paddingTop+paddingBottom,
                    childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);
            if (childLeft + childWidth + paddingRight > selfWidth) {
                childLeft=paddingLeft;
                childTop+=mVerticalSpacing+lineHeight;
                lineHeight=childHeight;
            }else {
                childLeft+=childWidth+mHorizontalSpacing;
            }
        }
        int wantedHeight=childTop+lineHeight+paddingBottom;
        setMeasuredDimension(selfWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    //onLayout这个方法里面，我们做的事情是，计算子控件出现的位置，它具有五个参数，
    //第一个参数是通知我们的控件是否发生了改变，还有四个参数描述了了我们的控件的位置。我们会根据控件的显隐状态
    //来判断这个控件是否要来加载，还要根据传过来的参数来绘制这个控件
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth=r-1;
        int paddingLeft=getPaddingLeft();
        int paddingTop=getPaddingTop();
        int paddingRight=getPaddingRight();

        int childLeft=paddingLeft;
        int childTop = paddingTop;
        int lineHeight=0;

        for (int i=0,childCount=getChildCount();i<childCount;++i) {
            View childeView = getChildAt(i);

            if (childeView.getVisibility() == View.GONE) {
                continue;
            }
            int childWidth = childeView.getMeasuredWidth();
            int childHeight = childeView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);
            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft=paddingLeft;
                childTop+=mVerticalSpacing+lineHeight;
                lineHeight = childHeight;
            }
            childeView.layout(childLeft,childTop,childLeft+childWidth,childTop+childHeight);
            childLeft += childWidth + mHorizontalSpacing;
        }
    }


}
