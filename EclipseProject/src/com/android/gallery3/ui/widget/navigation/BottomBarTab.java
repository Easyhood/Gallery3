package com.android.gallery3.ui.widget.navigation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by Fernflower decompiler)
//


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

import com.android.gallery3.R;

public class BottomBarTab extends RelativeLayout {
 public ImageView imageView;
 public TextView textView;
 public int color;
 private ArrayList<Animator> animatorList = new ArrayList();

 public BottomBarTab(Context context) {
     super(context);
     LayoutInflater.from(context).inflate(R.layout.item_bottom_navigation, this, true);
     this.imageView = (ImageView)this.findViewById(R.id.imageView);
     this.textView = (TextView)this.findViewById(R.id.textView);
 }

 public void setImageResource(int imageResource) {
     this.imageView.setImageResource(imageResource);
 }

 public void setText(String text) {
     this.textView.setText(text);
 }

 public void setSelected(boolean selected) {
     this.imageView.setSelected(selected);
 }

 public boolean isSelected() {
     return this.imageView.isSelected();
 }

 public void widthAnimator(int startWidth, int endWidth) {
     final LayoutParams params = (LayoutParams)this.getLayoutParams();
     ValueAnimator w = ValueAnimator.ofInt(new int[]{startWidth, endWidth});
     w.addUpdateListener(new AnimatorUpdateListener() {
         public void onAnimationUpdate(ValueAnimator animation) {
             params.width = ((Integer)animation.getAnimatedValue()).intValue();
             BottomBarTab.this.requestLayout();
         }
     });
     this.animatorList.add(w);
 }

 public void textScaleAnimator(float endScale) {
     ObjectAnimator sx = ObjectAnimator.ofFloat(this.textView, "scaleX", new float[]{endScale});
     ObjectAnimator sy = ObjectAnimator.ofFloat(this.textView, "scaleY", new float[]{endScale});
     this.animatorList.add(sx);
     this.animatorList.add(sy);
 }

 public void imageTranslationAnimator(float startY, float endY) {
     Log.i("etong", "endY: " + endY);
     ObjectAnimator ty = ObjectAnimator.ofFloat(this.imageView, "Y", new float[]{startY, endY});
     this.animatorList.add(ty);
 }

 public void animatorStart(int duration) {
     AnimatorSet animatorSet = new AnimatorSet();
     animatorSet.setDuration((long)duration);
     animatorSet.playTogether(this.animatorList);
     animatorSet.addListener(new AnimatorListenerAdapter() {
         public void onAnimationEnd(Animator animation) {
             BottomBarTab.this.animatorList.clear();
         }
     });
     animatorSet.start();
 }
}

