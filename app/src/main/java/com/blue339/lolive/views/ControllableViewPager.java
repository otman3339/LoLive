package com.blue339.lolive.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Blue on 2016/5/19.
 */
public class ControllableViewPager extends ViewPager {

    private boolean isCanScroll = false;

    public ControllableViewPager(Context context) {
        super(context);
    }

    public ControllableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isCanScroll) {
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isCanScroll) {
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }
    }
}
