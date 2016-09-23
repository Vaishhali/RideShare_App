package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.EditVehiclesActivity;

import java.util.ArrayList;

/**
 * Created by RASHMI on 19-09-2016.
 */
public class MyVehiclesAdapter  extends RecyclerView.Adapter<MyVehiclesAdapter.MyVehiclesHolder> {

    private ArrayList<MyVehiclesModel> myVehiclesList;
    Activity mContext;

    public MyVehiclesAdapter(Activity context, ArrayList<MyVehiclesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
    }


    @Override
    public MyVehiclesAdapter.MyVehiclesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_vehicles_row_view, parent, false);

        return new MyVehiclesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyVehiclesAdapter.MyVehiclesHolder holder, final int position) {
        holder.layoutHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.layoutSubHeader.getVisibility() == View.VISIBLE) {
                    holder.layoutSubHeader.setVisibility(View.GONE);
                }
                else {
                    holder.layoutSubHeader.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterPopup(view, position);
            }
        });

        if(position == 1) {
            holder.tvCarName.setText("Ferrari California");
            holder.tvCarModel.setText("ART-TG 548");
            holder.tvMake.setText("8500 make");
            holder.tvColour.setText("Red Color");
            holder.tvAC.setText("AC");
        }
    }

    @Override
    public int getItemCount() {
        return 2;//myVehiclesList.size()
    }

    public class MyVehiclesHolder extends RecyclerView.ViewHolder {
        public TextView tvMake, tvColour, tvAC, tvCarName, tvCarModel;
        LinearLayout layoutSubHeader;
        RelativeLayout layoutHeader;
        ImageView ivMenu;

        public MyVehiclesHolder(View view) {
            super(view);
            layoutHeader = (RelativeLayout) view.findViewById(R.id.layoutHeader);
            layoutSubHeader = (LinearLayout) view.findViewById(R.id.layoutSubHeader);
            tvMake = (TextView) view.findViewById(R.id.tvMake);
            tvColour = (TextView) view.findViewById(R.id.tvColour);
            tvAC = (TextView) view.findViewById(R.id.tvAC);
            tvCarName = (TextView) view.findViewById(R.id.tvCarName);
            tvCarModel = (TextView) view.findViewById(R.id.tvCarModel);
            ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }

    // Display anchored popup menu based on view selected
    private void showFilterPopup(View v, int position) {
        PopupMenu popup = new PopupMenu(mContext, v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.edit_menu, popup.getMenu());
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Intent intent = new Intent(mContext, EditVehiclesActivity.class);
                        mContext.startActivity(intent);
                        mContext.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//                        Toast.makeText(MainActivity.this, "Keyword!", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_popularity:
//                        Toast.makeText(MainActivity.this, "Popularity!", Toast.LENGTH_SHORT).show();
//                        return true;
                    default:
                        return false;
                }
            }
        });
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show();
    }
}
