package com.medum.medum.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.medum.medum.R;
import com.medum.medum.view.fragments.FavFragment;
import com.medum.medum.view.fragments.HomeFragment;
import com.medum.medum.view.fragments.ProfileFragment;
import com.medum.medum.view.fragments.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomBar bottombar = (BottomBar) findViewById(R.id.bottombar);
        bottombar.setDefaultTab(R.id.tab_home);

        bottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                        HomeFragment homefragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homefragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
                        break;
                    case R.id.tab_fav:
                        FavFragment favfragment = new FavFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,favfragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.tab_search:
                        SearchFragment searchfragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchfragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.tab_profile:
                        ProfileFragment profilefragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profilefragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }
}
