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
public class PreferredDestinationAdapter extends RecyclerView.Adapter<PreferredDestinationAdapter.PreferredDestinationHolder> {

    private ArrayList<MyVehiclesModel> myVehiclesList;
    Activity mContext;

    public PreferredDestinationAdapter(Activity context, ArrayList<MyVehiclesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
    }


    @Override
    public PreferredDestinationAdapter.PreferredDestinationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferred_destination_row_view, parent, false);

        return new PreferredDestinationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PreferredDestinationAdapter.PreferredDestinationHolder holder, final int position) {
//        if(position == 1) {
            holder.tvDestination.setText("Destination 0"+(position+1));
//            holder.tvCarModel.setText("ART-TG 548");
//            holder.tvMake.setText("8500 make");
//            holder.tvColour.setText("Red Color");
//            holder.tvAC.setText("AC");
//        }
    }

    @Override
    public int getItemCount() {
        return 5;//myVehiclesList.size()
    }

    public class PreferredDestinationHolder extends RecyclerView.ViewHolder {
        public TextView tvDestination;//, tvColour, tvAC, tvCarName, tvCarModel;
//        LinearLayout layoutSubHeader;
//        RelativeLayout layoutHeader;
//        ImageView ivMenu;

        public PreferredDestinationHolder(View view) {
            super(view);
//            layoutHeader = (RelativeLayout) view.findViewById(R.id.layoutHeader);
//            layoutSubHeader = (LinearLayout) view.findViewById(R.id.layoutSubHeader);
            tvDestination = (TextView) view.findViewById(R.id.tvDestination);
//            tvColour = (TextView) view.findViewById(R.id.tvColour);
//            tvAC = (TextView) view.findViewById(R.id.tvAC);
//            tvCarName = (TextView) view.findViewById(R.id.tvCarName);
//            tvCarModel = (TextView) view.findViewById(R.id.tvCarModel);
//            ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }
}
