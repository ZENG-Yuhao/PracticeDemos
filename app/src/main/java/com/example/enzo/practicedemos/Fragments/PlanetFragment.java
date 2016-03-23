package com.example.enzo.practicedemos.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enzo.practicedemos.R;


public class PlanetFragment extends Fragment {
    public final static String ARG_PLANET_NUMBER = "number";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet, null);
    }
}
