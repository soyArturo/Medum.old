package com.medum.medum.view;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.cast.Cast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.medum.medum.R;
import com.medum.medum.model.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewPostActivity extends AppCompatActivity {

    public static final String HOUSE_ID = "com.medum.medum.model.houseId";
    public static final String HOUSE_TITLE = "com.medum.medum.model.houseTitle";
    public static final String HOUSE_TYPE = "com.medum.medum.model.houseType";
    public static final String HOUSE_POST_TYPE = "com.medum.medum.model.postType";
    public static final String HOUSE_DESCRIPTION = "com.medum.medum.model.descriptionPost";
    public static final String HOUSE_DIRECTION = "com.medum.medum.model.directionPost";
    public static final String HOUSE_PRICE = "com.medum.medum.model.pricePost";

    TextInputEditText houseTitle;
    Spinner spinner;
    Spinner spinner2;
    TextInputEditText descriptionpost;
    TextInputEditText locationpost;
    TextInputEditText pricepost;
    Button savepost;

    DatabaseReference databaseHouse;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseHouse= FirebaseDatabase.getInstance().getReference("houses");
        houseTitle = (TextInputEditText) findViewById(R.id.titlepost);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        descriptionpost =(TextInputEditText) findViewById(R.id.descriptionpost);
        locationpost = (TextInputEditText) findViewById(R.id.locationpostform);
        pricepost = (TextInputEditText) findViewById(R.id.pricepost);
        savepost = (Button) findViewById(R.id.savepost);


        savepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHouse();
            }

        });

        showtoolbar("New Post",true);
    }

    private void addHouse(){
        String Title = houseTitle.getText().toString().trim();
        String Type=spinner.getSelectedItem().toString();
        String postType=spinner2.getSelectedItem().toString();
        String description=descriptionpost.getText().toString().trim();
        String direction=locationpost.getText().toString().trim();
        String price= pricepost.getText().toString().trim();

        if(!TextUtils.isEmpty(Title)&&!TextUtils.isEmpty(Type)&&!TextUtils.isEmpty(postType)&&!TextUtils.isEmpty(description)&&!TextUtils.isEmpty(price)){
            String userid = user.getUid();
            String id = databaseHouse.push().getKey();

            House house  = new House(userid,id,Title,Type,postType,description,direction,price);

            databaseHouse.child(id).setValue(house);

            houseTitle.setText("");
            descriptionpost.setText("");
            locationpost.setText("");
            pricepost.setText("");

            Toast.makeText(this,"Casa Publicada",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Por favor llenar los datos",Toast.LENGTH_LONG).show();
        }
    }

    public void showtoolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
