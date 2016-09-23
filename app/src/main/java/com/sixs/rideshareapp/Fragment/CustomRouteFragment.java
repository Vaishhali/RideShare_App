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
public class CustomRouteFragment extends Fragment {

    public CustomRouteFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.preferred_destination_fragment, container, false);

        TextView txtLabel = (TextView) rootView.findViewById(R.id.txtLabel);
        txtLabel.setText("CustomRouteFragment");
        return rootView;
    }
}
