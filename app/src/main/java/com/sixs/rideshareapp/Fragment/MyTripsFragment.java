package com.sixs.rideshareapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.adapter.HomeRoutesAdapter;

import java.util.ArrayList;

/**
 * Created by RASHMI on 14-09-2016.
 */
public class MyTripsFragment extends Fragment {

    private RecyclerView myVehiclesRecyclerview;
    HomeRoutesAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    public MyTripsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_trip_fragment_view, container, false);
        setView(rootView);

        return rootView;
    }

    private void setView(View view) {
        myVehiclesRecyclerview = (RecyclerView) view.findViewById(R.id.recycler_view);
        myVehiclesAdapter = new HomeRoutesAdapter(getActivity(), myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
    }
}
