package com.medum.medum.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medum.medum.R;
import com.medum.medum.adapter.PictureAdapterRecyclerView;
import com.medum.medum.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showtoolbar("",false,view);
        RecyclerView cardsRecycler = (RecyclerView) view.findViewById(R.id.cardrecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cardsRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPict(),R.layout.cardview_picture,getActivity());
        cardsRecycler.setAdapter(pictureAdapterRecyclerView);
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
