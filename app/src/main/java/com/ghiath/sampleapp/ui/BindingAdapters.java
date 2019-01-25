package com.ghiath.sampleapp.ui;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.databinding.BindingAdapter;


public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    @BindingAdapter("setTextFromInt")
    public static void setTextInt(View view, int txt) {
        ((TextView)view).setText(String.valueOf(txt));
    }
    }