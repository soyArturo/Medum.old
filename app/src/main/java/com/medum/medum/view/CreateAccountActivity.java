package com.medum.medum.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.medum.medum.R;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CreateAccount";

    private EditText textmail;
    private EditText textpassword;
    //private AppCompatEditText textrepeatpassword;
    private Button Join;
    private ProgressDialog progressDialog;

    //[Start auth]
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //[End auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //[Start init_auth]
        mAuth = FirebaseAuth.getInstance();
        //[End init_auth]

        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    finish();

                    startActivity(new Intent(getApplicationContext(), ContainerActivity.class));
                }


            }
        };

        //Text
        textmail = (EditText) findViewById(R.id.mail);
        textpassword = (EditText) findViewById(R.id.pass);

        //Button
        Join = (Button) findViewById(R.id.join);

        progressDialog = new ProgressDialog(this);

        Join.setOnClickListener(this);

        showtoolbar(getResources().getString(R.string.toolbartittle),true);
    }

    public void showtoolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void registerUser(){

        String email = textmail.getText().toString().trim();
        String password = textpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Ingresar correo por favor",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Ingresar contraseña por favor",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Creando registro, por favor esperar....");
        progressDialog.show();

        //Create a new user
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),ContainerActivity.class));
                        }else{
                            Toast.makeText(CreateAccountActivity.this,"Error en el Registro",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        registerUser();
    }
}
