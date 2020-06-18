package com.lorenzotribuiani.tailorin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstRun_Login extends AppCompatActivity {

    private final static int REGISTRATION_REQUEST = 102;

    private final static int NO_LOGIN = 104;
    private final static int LOGIN = 105;

    private EditText ET_mail, ET_password;
    private Button login, register, noLogin;
    private LinearLayout facebook_login;
    private ProgressBar bar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstrun_login);

        ET_mail = findViewById(R.id.Mail);
        ET_password = findViewById(R.id.Password);

        login = findViewById(R.id.Login_button);
        facebook_login = findViewById(R.id.Facebook_login);
        register = findViewById(R.id.Register_button);
        noLogin = findViewById(R.id.NoLogin_button);

        bar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        //LOGIN
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    bar.setVisibility(View.VISIBLE);
                    String mail = ET_mail.getText().toString();
                    String pass = ET_password.getText().toString();

                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                setResult(LOGIN);
                                finish();
                            } else {
                                //Email o password errati o utente non esistente
                                bar.setVisibility(View.INVISIBLE);
                                Toast.makeText(FirstRun_Login.this, getString(R.string.mailOrPassErr), Toast.LENGTH_LONG).show();
                                ET_password.setText("");
                                ET_password.setError(getString(R.string.Retry));
                                ET_mail.setText("");
                                ET_mail.setError(getString(R.string.Retry));
                            }
                        }
                    });
                } catch(Exception e){
                    //campi vuoti
                    bar.setVisibility(View.INVISIBLE);
                    if(TextUtils.isEmpty(ET_mail.getText().toString())) ET_mail.setError(getString(R.string.emptyField));
                    if(TextUtils.isEmpty(ET_password.getText().toString())) ET_password.setError(getString(R.string.emptyField));
                }

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
                setResult(NO_LOGIN);
                finish();
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

