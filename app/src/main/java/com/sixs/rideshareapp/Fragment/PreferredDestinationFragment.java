package com.sixs.rideshareapp.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.AddDestinationActivity;
import com.sixs.rideshareapp.activity.TripDetailsActivity;
import com.sixs.rideshareapp.adapter.PreferredDestinationAdapter;

import java.util.ArrayList;

/**
 * Created by RASHMI on 14-09-2016.
 */
public class PreferredDestinationFragment extends Fragment implements FloatingActionButton.OnClickListener {

    public PreferredDestinationFragment(){}

    FloatingActionButton fabAddDestination;
    private RecyclerView myVehiclesRecyclerview;
    PreferredDestinationAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.preferred_destination_fragment, container, false);

        setView(rootView);
        return rootView;
    }

    private void setView(View view) {
        fabAddDestination = (FloatingActionButton) view.findViewById(R.id.fabAddDestination);
        fabAddDestination.setOnClickListener(this);

        myVehiclesRecyclerview = (RecyclerView) view.findViewById(R.id.recycler_view);
//        myVehiclesRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        myVehiclesRecyclerview.setHasFixedSize(true);

        myVehiclesAdapter = new PreferredDestinationAdapter(getActivity(), myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
        myVehiclesAdapter.setOnItemClickListener(new PreferredDestinationAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.v("TTT", "On click");
//                Intent intent2 = new Intent(getActivity(), TripDetailsActivity.class);
//                startActivity(intent2);
//                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fabAddDestination:
                Intent intent = new Intent(getActivity(), AddDestinationActivity.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                break;
        }
    }
}