package com.example.douhaopeng.zhbjas1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.douhaopeng.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017\9\24 0024.
 */

public class GuideActivity extends Activity{
    private int[] mImageIds = new int[]{
      R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3
    };
    private ArrayList<ImageView> mImageViewList;
    private ViewPager mViewPager;
    private Button btnStart;
    private ImageView ivRedPoint;
    private LinearLayout llContainer;
    private int mPointDis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
       // ViewPager vp_guide ,Button btn_start, LinearLayout ll_container,ImageView iv_red_point
         mViewPager = findViewById(R.id.vp_guide);
         llContainer = findViewById(R.id.ll_container);
         btnStart = findViewById(R.id.btn_start);
         ivRedPoint = findViewById(R.id.iv_red_point);
        initData();
        mViewPager.setAdapter(new GuideAdapter());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滑动过程中的回调
                int leftMargin = (int)(position * positionOffset)+position * mPointDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                params.leftMargin = leftMargin;
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            //某个页面被选中
                if(position == mImageViewList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);//显示开始体验的按钮
                }
                else{
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面状态发生变化的回调
            }
        });
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除监听,避免重复回调
                ivRedPoint.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
                // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft()
                        - llContainer.getChildAt(0).getLeft();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.setBoolean(getApplicationContext(),"is_first_enter",false);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }

    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for(int i = 0;i<mImageIds.length;i++){
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i > 0){
                params.leftMargin = 10;//从第二个点开始设置左边距
            }
            point.setLayoutParams(params);
            llContainer.addView(point);

        }
    }

     class GuideAdapter extends PagerAdapter {
         @Override
         public int getCount() {
             return mImageViewList.size();
         }

         @Override
         public boolean isViewFromObject(View view, Object object) {
             return view == object;
         }

         @Override
         public Object instantiateItem(ViewGroup container, int position) {
             ImageView view  = mImageViewList.get(position);
             container.addView(view);
             return view;
         }

         @Override
         public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
         }
     }
}
