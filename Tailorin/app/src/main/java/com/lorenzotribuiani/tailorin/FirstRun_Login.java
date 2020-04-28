package com.lorenzotribuiani.tailorin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class FirstRun_Login extends AppCompatActivity {

    private final static int REGISTRATION_REQUEST = 102;

    private final static int NO_LOGIN = 104;
    private final static int LOGIN = 105;

    private EditText mail, password;
    private Button login, register, noLogin;
    private LinearLayout facebook_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstrun_login);

        mail = findViewById(R.id.Mail);
        password = findViewById(R.id.Password);

        login = findViewById(R.id.Login_button);
        facebook_login = findViewById(R.id.Facebook_login);
        register = findViewById(R.id.Register_button);
        noLogin = findViewById(R.id.NoLogin_button);

        //LOGIN
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //LOGIN FACEBOOK
        facebook_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //REGISTRAZIONE
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FirstRun_Login.this, Registration.class), REGISTRATION_REQUEST);
                Animatoo.animateSlideUp(FirstRun_Login.this);
            }
        });

        //ACCESSO SENZA LOGIN
        noLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            setResult(LOGIN);
            finish();
        }
    }
}

