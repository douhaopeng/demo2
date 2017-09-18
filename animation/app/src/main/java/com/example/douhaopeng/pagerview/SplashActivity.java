package com.example.douhaopeng.pagerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.douhaopeng.utils.PrefUtils;

/**
 * Created by Administrator on 2017\9\18 0018.
 */

public class SplashActivity extends Activity {
    private RelativeLayout rlRoot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        RotateAnimation animRotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);

        ScaleAnimation animScale =new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);

       AlphaAnimation animAlpha =  new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animAlpha);
        set.addAnimation(animScale);
        rlRoot.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isFirstEnter = PrefUtils.getBoolean(SplashActivity.this,"is_first_enter",true);
                Intent intent;
                if(isFirstEnter){
                    intent = new Intent(getApplicationContext(), GuideActivity.class);
                }else{
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                }


                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
