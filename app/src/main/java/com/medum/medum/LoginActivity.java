package com.medum.medum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.medum.medum.view.ContainerActivity;
import com.medum.medum.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG="LoginActivity";
    private static final int RC_SIGN_IN=9001;

    private Button signin;
    private EditText textmail;
    private EditText textpassword;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
    private SignInButton loginG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();*/

        if(mAuth.getCurrentUser()!=null){
            finish();

            startActivity(new Intent(getApplicationContext(), ContainerActivity.class));
        }

        textmail = (EditText) findViewById(R.id.username);
        textpassword = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.login);
        loginG = (SignInButton) findViewById(R.id.loginG);


        progressDialog = new ProgressDialog(this);

        signin.setOnClickListener(this);
        loginG.setOnClickListener(this);
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

    private void userLoginG(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    public void goCreateAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount acct = result.getSignInAccount();
                firebaseAuthWithGoogle(acct);
            }
        }
    }*/

   /* private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG,"succes");
        progressDialog.setMessage("Ingresando, por favor espere");
        progressDialog.show();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),ContainerActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                userLogin();
                break;
            case R.id.loginG:
                userLoginG();
                break;
        }
    }
}
