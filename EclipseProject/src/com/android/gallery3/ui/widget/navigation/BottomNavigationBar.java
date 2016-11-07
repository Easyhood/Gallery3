package com.android.gallery3.ui.widget.navigation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by Fernflower decompiler)
//


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class BottomNavigationBar extends LinearLayout {
 private Paint paint;
 private int cx;
 private int cy;
 private int r;
 private int currentColor;
 private ArrayList<BottomBarTab> tabList = new ArrayList();
 private BottomNavigationBar.TabListener tabListener;
 private int tabSelectedWidth;
 private int tabDefaultWidth;
 private int imageSelectedTop;
 private int imageDefaultTop;
 private float textDefaultScale;
 private int currentPosition;
 public int animation_duration = 150;
 private float tabWidthSelectedScale = 1.0F;
 public boolean textDefaultVisible = false;
 private boolean colorful = true;
 private int textSelectedColor;
 private int textDefaultColor;

 public BottomNavigationBar(Context context, AttributeSet attrs) {
     super(context, attrs);
     this.setOrientation(0);
     this.paint = new Paint(1);
     this.setGravity(1);
     this.imageSelectedTop = (int)(Resources.getSystem().getDisplayMetrics().scaledDensity * 6.0F);
     this.imageDefaultTop = (int)(Resources.getSystem().getDisplayMetrics().scaledDensity * 16.0F);
 }

 protected void onLayout(boolean changed, int l, int t, int r, int b) {
     super.onLayout(changed, l, t, r, b);
     if(changed) {
         this.tabDefaultWidth = (int)((float)this.getWidth() / ((float)this.tabList.size() + this.tabWidthSelectedScale - 1.0F));
         this.tabSelectedWidth = (int)((float)this.tabDefaultWidth * this.tabWidthSelectedScale);

         for(int i = 0; i < this.getChildCount(); ++i) {
             BottomBarTab tab = (BottomBarTab)this.getChildAt(i);
             LayoutParams params = (LayoutParams)tab.getLayoutParams();
             if(i == this.currentPosition) {
                 this.currentColor = tab.color;
                 params.width = this.tabSelectedWidth;
                 tab.textView.setVisibility(0);
                 tab.imageView.setY((float)this.imageSelectedTop);
                 tab.setSelected(true);
             } else {
                 params.width = this.tabDefaultWidth;
                 tab.imageView.setY((float)this.imageDefaultTop);
                 tab.textView.setScaleX(this.textDefaultScale);
                 tab.textView.setScaleY(this.textDefaultScale);
                 tab.setSelected(false);
             }
         }

         this.setBackgroundColor(this.currentColor);
     }

 }

 public void setSelected(int position) {
     this.currentPosition = position;
     this.currentColor = ((BottomBarTab)this.tabList.get(position)).color;

     for(int i = 0; i < this.tabList.size(); ++i) {
         BottomBarTab tab = (BottomBarTab)this.tabList.get(i);
         if(i == position) {
             tab.setSelected(true);
         } else {
             tab.setSelected(false);
         }
     }

 }

 public void setTabWidthSelectedScale(float tabWidthSelectedScale) {
     this.tabWidthSelectedScale = tabWidthSelectedScale;
 }

 public void setTextDefaultVisible(boolean visible) {
     this.textDefaultVisible = visible;
     if(this.textDefaultVisible) {
         this.imageDefaultTop = (int)(Resources.getSystem().getDisplayMetrics().scaledDensity * 8.0F);
         this.textDefaultScale = 0.9F;
     } else {
         this.imageDefaultTop = (int)(Resources.getSystem().getDisplayMetrics().scaledDensity * 16.0F);
         this.textDefaultScale = 0.0F;
     }

 }

 public void setColorful(boolean colorful) {
     this.colorful = colorful;
 }

 public void setTextColorResId(int textColorResId) {
     ColorStateList stateList = this.getResources().getColorStateList(textColorResId);
     this.textSelectedColor = stateList.getColorForState(View.SELECTED_STATE_SET, -1);
     this.textDefaultColor = stateList.getDefaultColor();
 }

 public void ripple(View view, int color) {
     int x = (int)(view.getX() + (float)(view.getWidth() / 2));
     int y = (int)(view.getY() + (float)(view.getHeight() / 2));
     if(this.colorful) {
         this.drawCircle(x, y, this.getWidth(), color);
     } else {
         this.drawCircle(x, y, this.getHeight() / 2, this.textSelectedColor);
     }

 }

 private void drawCircle(int cx, int cy, int maxR, int color) {
     this.cx = cx;
     this.cy = cy;
     this.currentColor = color;
     ValueAnimator valueAnimator = ValueAnimator.ofInt(new int[]{0, maxR});
     valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
         public void onAnimationUpdate(ValueAnimator animation) {
             BottomNavigationBar.this.r = ((Integer)animation.getAnimatedValue()).intValue();
             BottomNavigationBar.this.invalidate();
         }
     });
     valueAnimator.addListener(new AnimatorListenerAdapter() {
         public void onAnimationEnd(Animator animation) {
             BottomNavigationBar.this.setBackgroundColor(BottomNavigationBar.this.currentColor);
         }
     });
     valueAnimator.setDuration(300L);
     valueAnimator.start();
 }

 protected void onDraw(Canvas canvas) {
     super.onDraw(canvas);
     this.paint.setColor(this.currentColor);
     canvas.drawCircle((float)this.cx, (float)this.cy, (float)this.r, this.paint);
 }

 public void addTab(int resId, String text) {
 }

 public void addTab(int resId, String text, int color) {
     BottomBarTab tab = new BottomBarTab(this.getContext());
     tab.setImageResource(resId);
     tab.setText(text);
     tab.color = color;
     tab.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
             BottomNavigationBar.this.handClickEvent((BottomBarTab)v);
         }
     });
     LayoutParams params = (LayoutParams)tab.getLayoutParams();
     if(params == null) {
         params = new LayoutParams(0, -1);
     }

     this.addView(tab, params);
     this.tabList.add(tab);
     this.postInvalidate();
 }

 public void handClickEvent(BottomBarTab selected) {
     for(int i = 0; i < this.tabList.size(); ++i) {
         BottomBarTab tab = (BottomBarTab)this.tabList.get(i);
         if(selected.equals(tab)) {
             this.tabListener.onSelected(tab, i);
             if(!tab.isSelected()) {
                 tab.setSelected(true);
                 tab.textView.setVisibility(0);
                 tab.widthAnimator(this.tabDefaultWidth, this.tabSelectedWidth);
                 tab.textScaleAnimator(1.0F);
                 tab.imageTranslationAnimator((float)this.imageDefaultTop, (float)this.imageSelectedTop);
                 tab.animatorStart(this.animation_duration);
                 this.ripple(tab, tab.color);
             }
         } else if(tab.isSelected()) {
             tab.setSelected(false);
             tab.widthAnimator(this.tabSelectedWidth, this.tabDefaultWidth);
             tab.textScaleAnimator(this.textDefaultScale);
             tab.imageTranslationAnimator((float)this.imageSelectedTop, (float)this.imageDefaultTop);
             tab.animatorStart(this.animation_duration);
         }
     }

 }

 public void setOnTabListener(BottomNavigationBar.TabListener tabListener) {
     this.tabListener = tabListener;
 }

 public interface TabListener {
     void onSelected(BottomBarTab var1, int var2);
 }
}

