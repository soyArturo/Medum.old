package com.medum.medum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.medum.medum.view.ContainerActivity;
import com.medum.medum.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signin;
    private com.google.android.gms.common.SignInButton signInButton;
    private AppCompatEditText textmail;
    private AppCompatEditText textpassword;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            finish();

            startActivity(new Intent(getApplicationContext(), ContainerActivity.class));
        }

        textmail = (AppCompatEditText) findViewById(R.id.username);
        textpassword = (AppCompatEditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.login);
        signInButton = (com.google.android.gms.common.SignInButton) findViewById(R.id.loginG);

        progressDialog = new ProgressDialog(this);

        signin.setOnClickListener(this);
    }

    private void userLogin(){
        String email = textmail.getText().toString().trim();
        String password = textpassword.getText().toString().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Ingresar correo por favor",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Ingresar contraseña por favor",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Ingresando, por favor espere");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),ContainerActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Correo/Clave erroneas",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void goCreateAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {
        if(view == signin){
            userLogin();
        }
    }
}
