package com.lorenzotribuiani.tailorin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Registration extends AppCompatActivity {

    private EditText ET_nome, ET_cognome, ET_mail, ET_pass, ET_pass_conf;
    private Button registrati;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ET_nome = findViewById(R.id.Nome);
        ET_cognome = findViewById(R.id.Cognome);
        ET_mail = findViewById(R.id.Mail);
        ET_pass = findViewById(R.id.Password);
        ET_pass_conf = findViewById(R.id.Password_confirm);

        registrati = findViewById(R.id.registration);

        mAuth = FirebaseAuth.getInstance();

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final String nome = ET_nome.getText().toString();
                    final String cognome = ET_cognome.getText().toString();
                    String mail = ET_mail.getText().toString();
                    String password = ET_pass.getText().toString();
                    String password_conf = ET_pass_conf.getText().toString();

                    if(password.compareTo(password_conf)!=0){
                        Toast.makeText(Registration.this, getString(R.string.passwordNonCoincidenti), Toast.LENGTH_LONG).show();
                    } else {
                        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(nome + cognome).build();
                                    user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            setResult(RESULT_OK);
                                            finish();
                                        }
                                    });

                                } else {
                                    Toast.makeText(Registration.this, getString(R.string.somethingWrong), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (NullPointerException e){
                    Toast.makeText(Registration.this, getString(R.string.campiVuoti), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //modifico l'animazione di ritorno all'activity precedente
    //per mantenere un layout fluido
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}
