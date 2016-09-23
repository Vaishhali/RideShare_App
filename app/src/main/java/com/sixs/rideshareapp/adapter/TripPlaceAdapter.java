package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;

import java.util.ArrayList;

/**
 * Created by RASHMI on 22-09-2016.
 */
public class TripPlaceAdapter  extends RecyclerView.Adapter<TripPlaceAdapter.TripPlaceHolder> {

    private ArrayList<MyVehiclesModel> myVehiclesList;
    Activity mContext;

    public TripPlaceAdapter(Activity context, ArrayList<MyVehiclesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
    }


    @Override
    public TripPlaceAdapter.TripPlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_place_row_view, parent, false);

        return new TripPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TripPlaceAdapter.TripPlaceHolder holder, final int position) {
        if(position == 0){
            holder.tvDestination.setText("Start from place");
        }else if(position == 1) {
            holder.tvDestination.setText("Place 2 in route");
        }else if(position == 2){
            holder.tvDestination.setText("Place 3 in route");
        }
    }

    @Override
    public int getItemCount() {
        return 3;//myVehiclesList.size()
    }

    public class TripPlaceHolder extends RecyclerView.ViewHolder {
        public TextView tvDestination;

        public TripPlaceHolder(View view) {
            super(view);
            tvDestination = (TextView) view.findViewById(R.id.tvDestination);
        }
    }
}
