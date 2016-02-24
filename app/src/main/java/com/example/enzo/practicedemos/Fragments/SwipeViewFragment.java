package com.example.enzo.practicedemos.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enzo.practicedemos.R;


public class SwipeViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_swipe_view,
//                container, false);
//        return rootView;

        View view  = (View) inflater.inflate(R.layout.fragment_swipe_view, null);
        return view;
    }
}
