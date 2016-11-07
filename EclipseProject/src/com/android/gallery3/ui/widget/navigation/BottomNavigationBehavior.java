package com.android.gallery3.ui.widget.navigation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by Fernflower decompiler)
//

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BottomNavigationBehavior<V extends View> extends Behavior<V> {
 public BottomNavigationBehavior(Context context, AttributeSet attrs) {
     super(context, attrs);
 }

 public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
     return (nestedScrollAxes & 2) != 0;
 }

 public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
     LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
     layoutParams.gravity = 80;
     return super.onLayoutChild(parent, child, layoutDirection);
 }

 public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed) {
     if(dy > 0) {
         if(child.getY() == (float)(coordinatorLayout.getHeight() - child.getHeight())) {
             child.animate().translationY((float)child.getHeight()).setDuration(200L).start();
         }
     } else if(child.getY() == (float)coordinatorLayout.getHeight()) {
         Log.i("etong", "onNestedPreScroll");
         child.animate().translationY(0.0F).setDuration(250L).start();
     }

 }

 public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target) {
     super.onStopNestedScroll(coordinatorLayout, child, target);
 }
}

