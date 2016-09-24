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

        //by default start drive selkected
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            tvStartDrive.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle_selected) );
            tvJoinTrip.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
            tvPrivate.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
        } else {
            tvStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
            tvJoinTrip.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
            tvPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
        }

        tvStartDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tvStartDrive.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle_selected) );
                    tvJoinTrip.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                    tvPrivate.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                } else {
                    tvStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
                    tvJoinTrip.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    tvPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                }
            }
        });

        tvJoinTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tvStartDrive.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                    tvJoinTrip.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle_selected) );
                    tvPrivate.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                } else {
                    tvStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    tvJoinTrip.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
                    tvPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                }
            }
        });

        tvPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tvStartDrive.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                    tvJoinTrip.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle) );
                    tvPrivate.setBackgroundDrawable( getResources().getDrawable(R.drawable.dotted_circle_selected) );
                } else {
                    tvStartDrive.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    tvJoinTrip.setBackground( getResources().getDrawable(R.drawable.dotted_circle));
                    tvPrivate.setBackground( getResources().getDrawable(R.drawable.dotted_circle_selected));
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
