package com.sixs.rideshareapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sixs.rideshareapp.Model.MyVehiclesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.adapter.HomeRoutesAdapter;
import com.sixs.rideshareapp.adapter.MyVehiclesAdapter;
import com.sixs.rideshareapp.adapter.VehicleAdapter;
import com.sixs.rideshareapp.adapter.VehicleSpinnerAdapter;

import java.util.ArrayList;

public class EditTripDetailsActivity extends AppCompatActivity {

    private int hour;
    private int minute;

    TextView tvTime;
//    AppCompatSpinner spinnerVehicle;
    static final int TIME_DIALOG_ID = 1111;
    private RecyclerView myVehiclesRecyclerview;
    VehicleAdapter myVehiclesAdapter;
    ArrayList<MyVehiclesModel> myVehiclesList = new ArrayList<MyVehiclesModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_trip_details_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Trip");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setView();
    }

    private void setView() {
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIME_DIALOG_ID);
            }
        });


        myVehiclesRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
//        myVehiclesRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        myVehiclesRecyclerview.setHasFixedSize(true);

        myVehiclesAdapter = new VehicleAdapter(EditTripDetailsActivity.this, myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);

        myVehiclesAdapter.setOnItemClickListener(new VehicleAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
//                v.setBackgroundColor(getResources().getColor(R.color.list_background_pressed));
//                Intent intent2 = new Intent(EditTripDetailsActivity.this, TripDetailsActivity.class);
//                startActivity(intent2);
//                overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });

//        ArrayList<MyVehiclesModel> list = new ArrayList<MyVehiclesModel>();
////        MyVehiclesModel model = new MyVehiclesModel();
////        model.setName("");
//
//
//        spinnerVehicle = (AppCompatSpinner) findViewById(R.id.spinnerVehicle);
//        VehicleSpinnerAdapter adapter = new VehicleSpinnerAdapter(EditTripDetailsActivity.this, R.layout.vehicle_spinner_dropdown_item, list);
////        adapter.setDropDownViewResource(R.layout.vehicle_spinner_dropdown_item);
//        spinnerVehicle.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("time", tvTime.getText());
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
        this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:

                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;

            updateTime(hour,minute);

        }

    };

    private static String utilTime(int value) {

        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }

    // Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        tvTime.setText(aTime);
    }
}
