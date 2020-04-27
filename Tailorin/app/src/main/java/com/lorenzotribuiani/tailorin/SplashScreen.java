package com.lorenzotribuiani.tailorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        logo = findViewById(R.id.Logo);
        text = findViewById(R.id.Text);

        text.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_n_translate_bottom));
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_n_translate_top));

        final Animation bow = AnimationBow(logo);

        logo.postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.startAnimation(bow);
            }
        }, 1200);

        logo.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HomePage.class));
                Animatoo.animateFade(SplashScreen.this);
            }
        }, 2000);
    }


    private Animation AnimationBow(final View target1){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.resize_small);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                target1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.resize_big));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animation;
    }
}
