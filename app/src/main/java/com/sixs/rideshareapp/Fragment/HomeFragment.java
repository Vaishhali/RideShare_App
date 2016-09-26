package com.sixs.rideshareapp.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.TripDetailsActivity;
import com.sixs.rideshareapp.adapter.HomeRoutesAdapter;

import java.util.ArrayList;

/**
 * Created by RASHMI on 14-09-2016.
 */
public class HomeFragment extends Fragment  {

    TextView tvStartDrive, tvJoinTrip, tvPrivate;
    RelativeLayout layoutStartDrive, layoutJoinTrips, layoutPrivate;

    private RecyclerView myVehiclesRecyclerview;
    HomeRoutesAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setView(rootView);
        return rootView;
    }

    private void setView(View view) {
        tvStartDrive = (TextView) view.findViewById(R.id.tvStartDrive);
        tvJoinTrip = (TextView) view.findViewById(R.id.tvJoinTrip);
        tvPrivate = (TextView) view.findViewById(R.id.tvPrivate) ;

        layoutStartDrive = (RelativeLayout) view.findViewById(R.id.layoutStartDrive);
        layoutJoinTrips = (RelativeLayout) view.findViewById(R.id.layoutJoinTrips);
        layoutPrivate = (RelativeLayout) view.findViewById(R.id.layoutPrivate);

        //by default start drive selkected
        tvStartDrive.setTextColor(getResources().getColor(android.R.color.white));
        tvJoinTrip.setTextColor(getResources().getColor(R.color.gray));
        tvPrivate.setTextColor(getResources().getColor(R.color.gray));

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            layoutStartDrive.setBackgroundResource(R.drawable.dotted_circle_selected) ;
            layoutJoinTrips.setBackgroundResource(R.drawable.dotted_circle);
            layoutPrivate.setBackgroundResource( R.drawable.dotted_circle) ;
        } else {
            layoutStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
            layoutJoinTrips.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
            layoutPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
        }

        tvStartDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStartDrive.setTextColor(getResources().getColor(android.R.color.white));
                tvJoinTrip.setTextColor(getResources().getColor(R.color.gray));
                tvPrivate.setTextColor(getResources().getColor(R.color.gray));

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layoutStartDrive.setBackgroundResource(R.drawable.dotted_circle_selected);
                    layoutJoinTrips.setBackgroundResource(R.drawable.dotted_circle);
                    layoutPrivate.setBackgroundResource(R.drawable.dotted_circle);
                } else {
                    layoutStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
                    layoutJoinTrips.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    layoutPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                }
            }
        });

        tvJoinTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvJoinTrip.setTextColor(getResources().getColor(android.R.color.white));
                tvStartDrive.setTextColor(getResources().getColor(R.color.gray));
                tvPrivate.setTextColor(getResources().getColor(R.color.gray));

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layoutStartDrive.setBackgroundResource(R.drawable.dotted_circle);
                    layoutJoinTrips.setBackgroundResource(R.drawable.dotted_circle_selected);
                    layoutPrivate.setBackgroundResource(R.drawable.dotted_circle);
                } else {
                    layoutStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    layoutJoinTrips.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
                    layoutPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                }
            }
        });

        tvPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPrivate.setTextColor(getResources().getColor(android.R.color.white));
                tvJoinTrip.setTextColor(getResources().getColor(R.color.gray));
                tvStartDrive.setTextColor(getResources().getColor(R.color.gray));

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layoutStartDrive.setBackgroundResource(R.drawable.dotted_circle);
                    layoutJoinTrips.setBackgroundResource(R.drawable.dotted_circle);
                    layoutPrivate.setBackgroundResource(R.drawable.dotted_circle_selected);
                } else {
                    layoutStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    layoutJoinTrips.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    layoutPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
                }
            }
        });

        myVehiclesRecyclerview = (RecyclerView) view.findViewById(R.id.recycler_view);
        myVehiclesAdapter = new HomeRoutesAdapter(getActivity(), myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
        myVehiclesAdapter.setOnItemClickListener(new HomeRoutesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent2 = new Intent(getActivity(), TripDetailsActivity.class);
                startActivity(intent2);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
    }
}
