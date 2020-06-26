package com.example.apollovideoslider.transformers;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import com.example.apollovideoslider.R;
import com.example.apollovideoslider.fragments.activity_fragment_view_pager;

public class SimpleTransformation implements ViewPager2.PageTransformer {
    private VideoView myvideo;
    private ImageView myimage;
    @Override
    public void transformPage(@NonNull View page, float position) {
        activity_fragment_view_pager.MY_POSITION = position;
        myvideo = (VideoView)page.findViewById(R.id.videoView2);
        myimage = (ImageView)page.findViewById(R.id.myimg);

        if (position == 0.0 ){
            activity_fragment_view_pager.MY_TEXTO = "play";
            myimage.setVisibility(View.VISIBLE);
            myvideo.start();
            if(myvideo.isPlaying()){
                myimage.setVisibility(View.GONE);
            }


            /*
            if(myvideo.isPlaying()){
                myvideo.seekTo(0);
                int currentPosition = myvideo.getCurrentPosition();
                System.out.println("currentPosition 2 es: " + currentPosition);
                if (currentPosition == 0) {
                    myimage.setVisibility(View.GONE);
                }
            }*/

        }else{
            activity_fragment_view_pager.MY_TEXTO = "pause";
            //myimage.setVisibility(View.VISIBLE);
        }
    }
}
