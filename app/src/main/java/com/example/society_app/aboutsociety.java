package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class aboutsociety extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private Handler sliderHandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutsociety);
        viewPager2=findViewById(R.id.viewPageimageslider);


        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.balaji));
        sliderItems.add(new SliderItem(R.drawable.hide_seek));
        sliderItems.add(new SliderItem(R.drawable.kissan));
        sliderItems.add(new SliderItem(R.drawable.kurkure));
        sliderItems.add(new SliderItem(R.drawable.maggi));
        sliderItems.add(new SliderItem(R.drawable.pedi));
        sliderItems.add(new SliderItem(R.drawable.saffola));
        sliderItems.add(new SliderItem(R.drawable.sprite));
        sliderItems.add(new SliderItem(R.drawable.sugar));
        sliderItems.add(new SliderItem(R.drawable.tropica));
        sliderItems.add(new SliderItem(R.drawable.star));



        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.45f+r+0.25f);
            }
        });


        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });

    }
    private  Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}
