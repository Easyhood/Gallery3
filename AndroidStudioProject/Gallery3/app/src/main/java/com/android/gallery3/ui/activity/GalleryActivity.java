package com.android.gallery3.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.gallery3.R;

public class GalleryActivity extends BaseActivity {

    private RadioGroup rgBottom;
    private RadioButton rbBottomLocal;
    private RadioButton rbBottomPic;
    private RadioGroup rgTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        rgTitle = (RadioGroup) findViewById(R.id.rg_title);
        rgBottom = (RadioGroup) findViewById(R.id.rg_bottom);
        rbBottomLocal = (RadioButton) findViewById(R.id.rb_bottom_local);
        rbBottomPic = (RadioButton) findViewById(R.id.rb_bottom_pic);

        Drawable drawableLocal = getResources().getDrawable(R.drawable.tab_local_selector);
        drawableLocal.setBounds(0,0,52,52);
        rbBottomLocal.setCompoundDrawables(null,drawableLocal,null,null);

        Drawable drawablePic = getResources().getDrawable(R.drawable.tab_pic_selector);
        drawablePic.setBounds(0,0,52,52);
        rbBottomPic.setCompoundDrawables(null,drawablePic,null,null);

        //默认勾选页
        rgTitle.check(R.id.rb_native);
        rgBottom.check(R.id.rb_bottom_local);
    }


}
