package com.example.apollovideoslider.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.concurrent.TimeUnit;

import com.example.apollovideoslider.R;

public class fragment_movies extends Fragment {
    private VideoView myvideo;
    private ImageView myimage;
    private String URLvideos;
    private String URLimages;
    private TextView textViewtest;
    private static int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();
    /*
    public static fragment_movies newInstance(String param1, String param2) {
        fragment_movies fragment = new fragment_movies();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            URLvideos = getArguments().getString(activity_fragment_view_pager.URLVIDEOS_KEY,"No Url");
            URLimages = getArguments().getString(activity_fragment_view_pager.URLIMAGES_KEY,"No Url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // CREANDO LAS IMAGENES
        myimage = (ImageView)view.findViewById(R.id.myimg);
        //Glide.with(this).load(URLimages).into(myimage);
        Glide.with(this).load(URLimages).diskCacheStrategy(DiskCacheStrategy.ALL).into(myimage);
        myimage.setVisibility(View.VISIBLE);

        // CREANDO LOS VIDEOS
        Uri uri=Uri.parse(URLvideos);
        myvideo = (VideoView)view.findViewById(R.id.videoView2);
        myvideo.setVideoURI(uri);
        textViewtest = (TextView)view.findViewById(R.id.textViewtest);

        myvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //myimage.setVisibility(View.VISIBLE);
                mp.setLooping(true);
                mp.start();


                eTime = mp.getDuration();
                sTime = mp.getCurrentPosition();
                if(oTime == 0){
                    System.out.println(eTime);
                    oTime =1;
                }
                hdlr.postDelayed(UpdateVideoTime, 100);

                if(activity_fragment_view_pager.MY_TEXTO == "pause"){
                    myimage.setVisibility(View.VISIBLE);
                    mp.pause();
                }
            }
        });


    }

    private Runnable UpdateVideoTime = new Runnable() {
        @Override
        public void run() {
            sTime = myvideo.getCurrentPosition();
            textViewtest.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))) );

            //System.out.println("*************timer"+TimeUnit.MILLISECONDS.toMinutes(sTime) +" : " + (TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime)) ));
            System.out.println("*************timer1   "+ TimeUnit.MILLISECONDS.toMillis(sTime) );
            if( TimeUnit.MILLISECONDS.toMillis(sTime) > 200){
                System.out.println("*************hola   ");
                myimage.setVisibility(View.GONE);
            }
            if( TimeUnit.MILLISECONDS.toMillis(sTime) == 0){
                myimage.setVisibility(View.VISIBLE);
            }
            hdlr.postDelayed(this, 100);
        }
    };

}
