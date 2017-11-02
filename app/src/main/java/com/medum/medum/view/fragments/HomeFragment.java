package com.medum.medum.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medum.medum.R;
import com.medum.medum.adapter.PictureAdapterRecyclerView;
import com.medum.medum.model.House;
import com.medum.medum.model.Picture;
import com.medum.medum.view.NewPostActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<House> houses;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Picture,PictureAdapterRecyclerView.PictureViewHolder> pictureAdapterRecyclerView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showtoolbar("",false,view);

        databaseReference = FirebaseDatabase.getInstance().getReference("houses");
        RecyclerView cardsRecycler = (RecyclerView) view.findViewById(R.id.cardrecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cardsRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPict(),R.layout.cardview_picture,getActivity());
        /*pictureAdapterRecyclerView = new FirebaseRecyclerAdapter<Picture, PictureAdapterRecyclerView.PictureViewHolder>(
                Picture.class,
                R.layout.cardview_picture,
                PictureAdapterRecyclerView.PictureViewHolder.class,
                databaseReference.child("houses")
        ) {


            @Override
            public PictureAdapterRecyclerView.PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            protected void onBindViewHolder(PictureAdapterRecyclerView.PictureViewHolder holder, int position, Picture model) {

            }

            protected void populatePictureViewHolder(PictureAdapterRecyclerView.PictureViewHolder pictureViewHolder, Picture picture,
                                              final int position){}
        };*/
        cardsRecycler.setAdapter(pictureAdapterRecyclerView);

        houses = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewPostActivity.class));
            }
        });
        return view;
    }

    public ArrayList<Picture> buildPict(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.construyehogar.com/wp-content/uploads/2016/02/Idea-fachada-casa-dos-pisos-moderna-coralhomes.com_.au-.jpg","Casa en venta", "200000", "4 recamaras", "5"));
        pictures.add(new Picture("http://weknowyourdreams.com/images/casa/casa-06.jpg","Casa en renta", "5000", "2 recamaras", "6"));
        pictures.add(new Picture("http://www.terrazasdelpacifico.mx/images/departamentos_en_Tijuana_Lunada_img1.jpg","Departamento en renta", "3000", "2 recamaras", "6"));
        return pictures;
    }

    public void showtoolbar(String tittle, boolean upButton,View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
