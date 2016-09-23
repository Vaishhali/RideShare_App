package com.sixs.rideshareapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.Util.Utils;
import com.sixs.rideshareapp.adapter.TripPlaceAdapter;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class TripDetailsActivity extends AppCompatActivity {

    private GoogleMap mMap;

    TextView tvTime;
    private RecyclerView myVehiclesRecyclerview;
    TripPlaceAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Trip Details");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setView();
    }

    private void setView() {
        tvTime = (TextView) findViewById(R.id.tvTime);

        myVehiclesRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
//        myVehiclesRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        myVehiclesRecyclerview.setHasFixedSize(true);

        myVehiclesAdapter = new TripPlaceAdapter(TripDetailsActivity.this, myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TripDetailsActivity.this.getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trip_detail_menu, menu);

        // return true so that the menu pop up is opened
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        else if (item.getItemId() == R.id.edit) {
            Intent intent = new Intent(TripDetailsActivity.this, EditTripDetailsActivity.class);
            startActivityForResult(intent, Utils.EDIT_TRIP);
            overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
        }
        else if (item.getItemId() == R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TripDetailsActivity.this, R.style.MyDialogTheme);
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

            String negativeText = TripDetailsActivity.this.getString(android.R.string.cancel);
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
            pbutton.setTextColor(getResources().getColor(R.color.blue_light));

            Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(getResources().getColor(R.color.black_light));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("TTT", "Onactivity result .... requestCode = " + requestCode + "  " + resultCode + "  " + Activity.RESULT_OK);
        if(requestCode == Utils.EDIT_TRIP) {
            Log.v("TTT", "Onactivity result .... resultCode = " + resultCode + "  " + Activity.RESULT_OK);
            if(resultCode == Activity.RESULT_OK) {
                String newTime = data.getStringExtra("time");
                Log.v("TTT", "Onactivity result .... Time = " + newTime);
                tvTime.setText(newTime);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
