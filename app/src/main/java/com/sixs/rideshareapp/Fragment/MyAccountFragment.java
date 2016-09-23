package com.sixs.rideshareapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixs.rideshareapp.R;

/**
 * Created by RASHMI on 14-09-2016.
 */
public class MyAccountFragment extends Fragment {

    public MyAccountFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_accounts_fragment_view, container, false);

        TextView txtLabel = (TextView) rootView.findViewById(R.id.txtLabel);
        txtLabel.setText("MyAccountFragment");

        return rootView;
    }
}
