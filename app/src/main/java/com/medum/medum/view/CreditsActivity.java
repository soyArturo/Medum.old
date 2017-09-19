package com.medum.medum.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.medum.medum.R;
import com.squareup.picasso.Picasso;

public class CreditsActivity extends AppCompatActivity {

    private ImageView medum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        medum = (ImageView) findViewById(R.id.medumlogo);
    }
}
