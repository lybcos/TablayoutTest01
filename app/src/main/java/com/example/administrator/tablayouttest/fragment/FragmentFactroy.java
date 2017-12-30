package com.example.administrator.tablayouttest.fragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/10/30.
 */

public class FragmentFactroy {
    private static final int TAB_HOME = 0;
    private static final int TAB_APP = 1;
    private static final int TAB_GAME = 2;
    private static final int TAB_SUBJECT = 3;
    private static final int TAB_RECOMMENT = 4;
    private static final int TAB_CATEGORY = 5;
    private static final int TAB_HOT = 6;
    private static  BaseFragment mfragment;
    private static HashMap<Integer,BaseFragment>map = new HashMap<Integer,BaseFragment>();
    public static BaseFragment createFragment(int position) {
        mfragment = map.get(position);
        if (null == mfragment) {
        switch (position) {
            case TAB_HOME:
                mfragment = new HomeFragment();
                break;
            case TAB_APP:
                mfragment = new AppFragment();
                break;
            case TAB_GAME:
                mfragment = new GameFragment();
                break;
            case TAB_SUBJECT:
                mfragment = new SubjectFragment();
                break;
            case TAB_RECOMMENT:
                mfragment = new RecommentFragement();
                break;
            case TAB_CATEGORY:
                mfragment = new CategoryFragment();
                break;
            case TAB_HOT:
                mfragment = new HotFragment();
                break;
        }
        map.put(position, mfragment);
        }
        return mfragment;
    }
}
