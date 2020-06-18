package com.lorenzotribuiani.tailorin;

import androidx.annotation.Nullable;
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

    private final static int LOGIN_REQUEST = 101;

    private final static int NO_LOGIN = 104;
    private final static int LOGIN = 105;

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
        if(preferences.getBoolean("isFirstRun", true)){

            logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    logo.startAnimation(bow);
                }
            }, 1200);

            logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivityForResult(new Intent(SplashScreen.this, FirstRun_Login.class), LOGIN_REQUEST);
                    //cambio il tipo di transizione fra le activity dallo scorrimento al fade (solo in questo caso)
                    Animatoo.animateFade(SplashScreen.this);
                }
            }, 2000);

        } else {

            if(preferences.getBoolean("isLogged", false)){
                logo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        logo.startAnimation(bow);
                    }
                }, 1200);

                logo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, HomePage.class);
                        intent.putExtra("isLogged", true);
                        //elimina la splashActivity dallo stack
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent);
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
                        Intent intent = new Intent(SplashScreen.this, HomePage.class);
                        intent.putExtra("isLogged", false);
                        //elimina la splashActivity dallo stack
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent);
                        //cambio il tipo di transizione fra le activity dallo scorrimento al fade (solo in questo caso)
                        Animatoo.animateFade(SplashScreen.this);
                    }
                }, 2000);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences preferences = getSharedPreferences("LoadCheck", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstRun", false);

        Intent intent = new Intent(this, HomePage.class);

        switch(resultCode){
            case LOGIN:
                editor.putBoolean("isLogged", true);
                editor.apply();
                intent.putExtra("isLogged", true);
                //Elimina la splash activity dallo stack
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case NO_LOGIN:
                editor.putBoolean("isLogged", false);
                editor.apply();
                intent.putExtra("isLogged", false);
                //elimina la splashActivity dallo stak
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
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
