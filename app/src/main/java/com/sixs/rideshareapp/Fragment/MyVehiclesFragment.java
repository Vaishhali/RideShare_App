package com.sixs.rideshareapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.AddVehiclesActivity;
import com.sixs.rideshareapp.adapter.MyVehiclesAdapter;

import java.util.ArrayList;

/**
 * Created by RASHMI on 14-09-2016.
 */
public class MyVehiclesFragment extends Fragment implements FloatingActionButton.OnClickListener {

    public MyVehiclesFragment(){}

    FloatingActionButton fabAddDestination;
    private RecyclerView myVehiclesRecyclerview;
    MyVehiclesAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_vehicles_view, container, false);
//        TextView txtLabel = (TextView) rootView.findViewById(R.id.txtLabel);
//        txtLabel.setText("MyVehiclesFragment");
        setView(rootView);
        return rootView;
    }

    private void setView(View view) {
        fabAddDestination = (FloatingActionButton) view.findViewById(R.id.fabAddDestination);
        fabAddDestination.setOnClickListener(this);

        myVehiclesRecyclerview = (RecyclerView) view.findViewById(R.id.recycler_view);
//        myVehiclesRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        myVehiclesRecyclerview.setHasFixedSize(true);

        myVehiclesAdapter = new MyVehiclesAdapter(getActivity(), myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fabAddDestination:
                Intent intent = new Intent(getActivity(), AddVehiclesActivity.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//                showDialog();
                break;
        }
    }

}
