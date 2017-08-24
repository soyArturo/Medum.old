package com.medum.medum.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.medum.medum.LoginActivity;
import com.medum.medum.R;

public class ProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()==null){
            finish();

            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        logout = (TextView) findViewById(R.id.logouttext);

        logout.setOnClickListener(this);

        showtoolbar("Configuracion",true);


    }

    public void showtoolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onClick(View view) {
        if(view == logout){
            mAuth.signOut();
            getFragmentManager().popBackStack();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
