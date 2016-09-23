package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixs.rideshareapp.R;

import java.util.ArrayList;

/**
 * Created by RASHMI on 23-09-2016.
 */
public class VehicleSpinnerAdapter extends ArrayAdapter<String> {

private Activity activity;
private ArrayList data;

LayoutInflater inflater;

/*************  CustomAdapter Constructor *****************/
public VehicleSpinnerAdapter(
        Activity activitySpinner,
        int textViewResourceId,
        ArrayList objects
        )
        {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
    Log.v("TTT", "in get view");
        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.vehicle_spinner_dropdown_item, parent, false);

        TextView tvCarName = (TextView)row.findViewById(R.id.tvCarName);
        TextView tvCarModel  = (TextView)row.findViewById(R.id.tvCarModel);

        if(position==0){
            // Default selected Spinner item
            tvCarName.setText("HONDA AMAZE");
            tvCarModel.setText("KLO1-AV 1234");
        }
        else if(position == 1) {
            tvCarName.setText("Ferrari California");
            tvCarModel.setText("ART-TG 548");
        }

        return row;
        }

    @Override
    public int getCount() {
        return 2;//super.getCount();
    }
}


