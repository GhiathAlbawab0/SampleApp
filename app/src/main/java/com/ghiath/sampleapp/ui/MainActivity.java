package com.ghiath.sampleapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.os.Bundle;

import com.ghiath.sampleapp.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportFragmentManager().beginTransaction().add(R.id.main_frag,new CategoriesFragment(),"tag").commit();
    }
}
