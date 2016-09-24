package com.sixs.rideshareapp.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixs.rideshareapp.R;

/**
 * Created by RASHMI on 22-09-2016.
 */
public class MyScoreFragment  extends Fragment {

    public MyScoreFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_score_view, container, false);

        return rootView;
    }
}