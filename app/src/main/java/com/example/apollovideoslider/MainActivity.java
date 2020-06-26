package com.example.apollovideoslider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.apollovideoslider.fragments.activity_fragment_view_pager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(MainActivity.this, activity_fragment_view_pager.class));
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_activity_view_pager);
        //setContentView(R.layout.fragment_movies);
    }
}
