package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixs.rideshareapp.Model.BlockCompaniesModel;
import com.sixs.rideshareapp.R;

import java.util.ArrayList;

/**
 * Created by RASHMI on 20-09-2016.
 */
public class BlockComapniesAdapter  extends RecyclerView.Adapter<BlockComapniesAdapter.BlockComapniesHolder> {

    private ArrayList<BlockCompaniesModel> myVehiclesList;
    Activity mContext;

    public BlockComapniesAdapter(Activity context, ArrayList<BlockCompaniesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
    }


    @Override
    public BlockComapniesAdapter.BlockComapniesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_comapnies_row_view, parent, false);

        return new BlockComapniesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BlockComapniesAdapter.BlockComapniesHolder holder, final int position) {
        holder.switchCompanyName.setText(myVehiclesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myVehiclesList.size();
    }

    public class BlockComapniesHolder extends RecyclerView.ViewHolder {
        SwitchCompat switchCompanyName;
//        public TextView tvMake, tvColour, tvAC;
//        LinearLayout layoutSubHeader;
//        RelativeLayout layoutHeader;
//        ImageView ivMenu;

        public BlockComapniesHolder(View view) {
            super(view);
            switchCompanyName = (SwitchCompat) view.findViewById(R.id.switchCompanyName);
//            layoutHeader = (RelativeLayout) view.findViewById(R.id.layoutHeader);
//            layoutSubHeader = (LinearLayout) view.findViewById(R.id.layoutSubHeader);
//            tvMake = (TextView) view.findViewById(R.id.tvMake);
//            ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }



}
