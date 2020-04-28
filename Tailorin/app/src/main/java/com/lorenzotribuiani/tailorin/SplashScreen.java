package com.lorenzotribuiani.tailorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashScreen extends AppCompatActivity {

    private int LOGIN_REQUEST = 101;

    private ImageView logo, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        logo = findViewById(R.id.Logo);
        text = findViewById(R.id.Text);

        //animazioni del fade iniziale
        text.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_n_translate_bottom));
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_n_translate_top));

        //ottengo l'animazione di rimbalzo
        final Animation bow = AnimationBow(logo);

        SharedPreferences preferences = getSharedPreferences("LoadCheck", MODE_PRIVATE);
        if(preferences.getBoolean("idFirstRun", true)){

            logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    logo.startAnimation(bow);
                }
            }, 1200);

            logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, FirstRun_Login.class));
                    //cambio il tipo di transizione fra le activity dallo scorrimento al fade (solo in questo caso)
                    Animatoo.animateFade(SplashScreen.this);
                }
            }, 2000);

        } else {

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
                    //cambio il tipo di transizione fra le activity dallo scorrimento al fade (solo in questo caso)
                    Animatoo.animateFade(SplashScreen.this);
                }
            }, 2000);

        }
    }


    private Animation AnimationBow(final ImageView target1){
        //ottengo la prima animazione che rimpicciolisce il logo
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.resize_small);

        //quando la prima termina inizia la seconda
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //imposto come drawable il logo animato
                target1.setImageDrawable(getDrawable(R.drawable.animated_logo_no_text));
                //faccio partire le animazioni della view e dell'immagine
                ((AnimatedVectorDrawable)target1.getDrawable()).start();
                target1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.resize_big));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animation;
    }
}
