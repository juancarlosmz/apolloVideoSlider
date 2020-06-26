package com.example.apollovideoslider;
import android.annotation.SuppressLint;
import androidx.viewpager2.widget.ViewPager2;
import com.example.apollovideoslider.transformers.SimpleTransformation;
public class Utils {
    @SuppressLint("NewApi")
    public static ViewPager2.PageTransformer getTransformer(int id) {
        switch (id) {
            case R.id.action_simple_transformation:
                return new SimpleTransformation();
        }
        return null;
    }
}
