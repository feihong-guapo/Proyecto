package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Form1 extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private List<Integer> mLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        mLayouts.add(R.layout.activity_form1);
        mLayouts.add(R.layout.activity_form2);
        mLayouts.add(R.layout.activity_form3);

        mViewPager = findViewById(R.id.viewPager);
//        PageAdapter adapter = new PageAdapter(this, mLayouts);
//        mViewPager.setAdapter(adapter);
    }
}