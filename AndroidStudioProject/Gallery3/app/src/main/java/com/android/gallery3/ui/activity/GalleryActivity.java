package com.android.gallery3.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.gallery3.R;
import com.android.gallery3.global.GlobalConstants;
import com.android.gallery3.ui.fragment.NativeFragment;
import com.android.gallery3.ui.fragment.PictureFragment;
import com.android.gallery3.utils.EventUtil;

import org.greenrobot.eventbus.EventBus;


public class GalleryActivity extends BaseActivity {

    private static final String TAG_NATIVE = "TAG_NATIVE";
    private static final String TAG_PICTURE = "TAG_PICTURE";

    private RadioGroup rgBottom;
    private RadioButton rbBottomLocal;
    private RadioButton rbBottomPic;
    public RadioGroup rgTitle;
    private RadioButton rbNative;
    private RadioButton rbLocation;
    private TextView tvShu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initView();
        initFragment();
        initPictureFragmentViewpager();

        //默认Fragment
        NativeFragment fragment = new NativeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,fragment);
        transaction.commit();
    }

    /**
     * 初始化布局
     */
    public void initView(){
        rgTitle = (RadioGroup) findViewById(R.id.rg_title);
        rgBottom = (RadioGroup) findViewById(R.id.rg_bottom);
        rbBottomLocal = (RadioButton) findViewById(R.id.rb_bottom_local);
        rbBottomPic = (RadioButton) findViewById(R.id.rb_bottom_pic);
        rbNative = (RadioButton) findViewById(R.id.rb_native);
        rbLocation = (RadioButton) findViewById(R.id.rb_location);
        tvShu = (TextView) findViewById(R.id.tv_shu);

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

    /**
     * 初始化fragment
     */
    public void initFragment(){

        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId){
                    case R.id.rb_bottom_local:
                        transaction.replace(R.id.fl_content,NativeFragment.newInstance(),TAG_NATIVE);
                        rbNative.setText(R.string.native_place);
                        tvShu.setVisibility(View.INVISIBLE);
                        rbLocation.setVisibility(View.INVISIBLE);
                        rgTitle.check(R.id.rb_native);
                        break;
                    case R.id.rb_bottom_pic:
                        transaction.replace(R.id.fl_content, PictureFragment.newInstance(),TAG_PICTURE);
                        rbNative.setText(R.string.time_lapse);
                        tvShu.setVisibility(View.VISIBLE);
                        rbLocation.setVisibility(View.VISIBLE);
                        break;
                    default:
                        transaction.replace(R.id.fl_content,NativeFragment.newInstance(),TAG_NATIVE);
                        rbNative.setText(R.string.native_place);
                        tvShu.setVisibility(View.INVISIBLE);
                        rbLocation.setVisibility(View.INVISIBLE);
                        rgTitle.check(R.id.rb_native);
                        break;
                }
                transaction.commitAllowingStateLoss();

            }
        });
        rgBottom.check(R.id.rb_bottom_local);

    }

    /**
     * 初始化picturefragment的viewpager的切换事件
     */
    public void initPictureFragmentViewpager(){
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_native:
                        EventBus.getDefault().post(new EventUtil(GlobalConstants.TIME_MSG));
                        break;
                    case R.id.rb_location:
                        EventBus.getDefault().post(new EventUtil(GlobalConstants.MAP_MSG));
                        break;
                    default:
                        EventBus.getDefault().post(new EventUtil(GlobalConstants.TIME_MSG));
                        break;
                }
            }
        });
    }

}
