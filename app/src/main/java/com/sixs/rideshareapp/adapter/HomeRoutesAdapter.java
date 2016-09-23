package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.EditTripDetailsActivity;
import com.sixs.rideshareapp.activity.TripDetailsActivity;

import java.util.ArrayList;

/**
 * Created by RASHMI on 22-09-2016.
 */
public class HomeRoutesAdapter  extends RecyclerView.Adapter<HomeRoutesAdapter.HomeRouteHolder> {

    private ArrayList<MyVehiclesModel> myVehiclesList;
    Activity mContext;
    private static ClickListener clickListener;

    public HomeRoutesAdapter(Activity context, ArrayList<MyVehiclesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
    }

    @Override
    public HomeRoutesAdapter.HomeRouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_route_row_view, parent, false);
        return new HomeRouteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeRoutesAdapter.HomeRouteHolder holder, final int position) {
//        holder.layoutHeader.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.layoutSubHeader.getVisibility() == View.VISIBLE) {
//                    holder.layoutSubHeader.setVisibility(View.GONE);
//                }
//                else {
//                    holder.layoutSubHeader.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterPopup(view, position);
            }
        });

        if(position == 1) {
            holder.tvRouteName.setText("Office to Home");
            holder.tvVehicleNumber.setText("KLO1-AV 1234");
            holder.tvRouter.setText("Delhi - Sydney");
//            holder.tvColour.setText("Red Color");
//            holder.tvAC.setText("AC");
        }
    }

    @Override
    public int getItemCount() {
        return 2;//myVehiclesList.size()
    }

    public class HomeRouteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvRouteName, tvVehicleNumber, tvRouter;//, tvCarName, tvCarModel;
//        LinearLayout layoutSubHeader;
//        RelativeLayout layoutHeader;
        ImageView ivMenu;

        public HomeRouteHolder(View view) {
            super(view);
            view.setOnClickListener(this);
//            layoutHeader = (RelativeLayout) view.findViewById(R.id.layoutHeader);
//            layoutSubHeader = (LinearLayout) view.findViewById(R.id.layoutSubHeader);
            tvRouteName = (TextView) view.findViewById(R.id.tvRouteName);
            tvVehicleNumber = (TextView) view.findViewById(R.id.tvVehicleNumber);
            tvRouter = (TextView) view.findViewById(R.id.tvRouter);
//            tvCarName = (TextView) view.findViewById(R.id.tvCarName);
//            tvCarModel = (TextView) view.findViewById(R.id.tvCarModel);
            ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
//        void onItemLongClick(int position, View v);
    }

    // Display anchored popup menu based on view selected
    private void showFilterPopup(View v, int position) {
        PopupMenu popup = new PopupMenu(mContext, v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.edit_delete_trip, popup.getMenu());
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Intent intent = new Intent(mContext, EditTripDetailsActivity.class);
                        mContext.startActivity(intent);
                        mContext.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                        return true;
                    case R.id.delete:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyDialogTheme);
                        builder.setTitle("Delete");
                        builder.setMessage("Do you want to delete this trip?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
//                                        RegisterSuccessActivity.this.finish();
                                    }
                                });

                        String negativeText = mContext.getString(android.R.string.cancel);
                        builder.setNegativeButton(negativeText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        // display dialog
                        dialog.show();
                        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        pbutton.setTextColor(mContext.getResources().getColor(R.color.blue_light));

                        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        nbutton.setTextColor(mContext.getResources().getColor(R.color.black_light));
                        return true;
//                    case R.id.details:
//                        Intent intent2 = new Intent(mContext, TripDetailsActivity.class);
//                        mContext.startActivity(intent2);
//                        mContext.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
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
