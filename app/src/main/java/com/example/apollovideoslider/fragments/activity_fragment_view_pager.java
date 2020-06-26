package com.example.apollovideoslider.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.apollovideoslider.R;
import com.example.apollovideoslider.databinding.FragmentActivityViewPagerBinding;
import com.example.apollovideoslider.transformers.SimpleTransformation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apollovideoslider.R;


public class activity_fragment_view_pager extends AppCompatActivity {
    FragmentActivityViewPagerBinding binding;
    private String[] titles = new String[]{"Movies", "Events", "Tickets","test","test1","test2","Movies", "Events", "Tickets","test","test1"};
    public static final String URLVIDEOS_KEY = "URLvideos";
    public static final String URLIMAGES_KEY = "URLimages";
    public static String MY_TEXTO = "";
    public static float MY_POSITION;
    /*
    public static activity_fragment_view_pager newInstance(String param1, String param2) {
        activity_fragment_view_pager fragment = new activity_fragment_view_pager();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentActivityViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }



    private void init() {
        System.out.println("este es el test");
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        binding.viewPager.setPageTransformer((ViewPager2.PageTransformer) new SimpleTransformation());
        // comentar para ocultar los attaching tab mediator
        // new TabLayoutMediator(binding.tabLayout, binding.viewPager,(tab, position) -> tab.setText(titles[position])).attach();
    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {public ViewPagerFragmentAdapter(@NonNull activity_fragment_view_pager fragmentActivity){super(fragmentActivity);}
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // creando la caja cotenedora de variables para movies, events
            fragment_movies moviesFragment = new fragment_movies();
            Bundle bundle = new Bundle();
            int j = 0;
            for(int i= 0; i < titles.length; i++){
                if(position == i){
                    j = i + 1;
                    bundle.putString( URLIMAGES_KEY, "https://apolomultimedia-server4.info/images/"+j+".jpg" );
                    bundle.putString( URLVIDEOS_KEY, "https://apolomultimedia-server4.info/videos/"+j+".mp4" );
                    moviesFragment.setArguments(bundle);
                }
            }
            return moviesFragment;
        }
        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
