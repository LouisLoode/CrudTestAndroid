package com.borislaporte.lasalle.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.borislaporte.lasalle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.splash_imageview)
    ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        startAnimation();
    }

    private void startAnimation() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(splashImageView, "scaleX", 10);
//        scaleXAnimator.setDuration(1000);
//        scaleXAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(splashImageView, "scaleY", 10);
//        scaleYAnimator.setDuration(1000);
//        scaleYAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(splashImageView, "alpha", 0);
//        alphaAnimator.setDuration(1000);
//        alphaAnimator.setInterpolator(new DecelerateInterpolator());

        AnimatorSet awesomeAnimation = new AnimatorSet();
        awesomeAnimation.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
        awesomeAnimation.setDuration(1000);
        awesomeAnimation.setStartDelay(1000);
        awesomeAnimation.setInterpolator(new DecelerateInterpolator());

        awesomeAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        awesomeAnimation.start();
    }
}
