package com.sixs.rideshareapp.activity;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sixs.rideshareapp.R;

public class AddVehiclesActivity extends AppCompatActivity {

    TextInputLayout inputLayoutRegistrationNo;
    TextView tvCancel, tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicles_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Vehicles");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setVIew();
    }

    private void setVIew() {
        inputLayoutRegistrationNo = (TextInputLayout) findViewById(R.id.inputLayoutRegistrationNo);

        ScrollView scrollview = (ScrollView) findViewById(R.id.scrollview);
        scrollview.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                Log.v("CHILD", "CHILD TOUCH");
                Log.v("TTTT", "On touch..............");
                ((LinearLayout) inputLayoutRegistrationNo.getParent()).clearFocus();
//                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                // Disallow the touch request for parent scroll on touch of
                // child view
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        tvCancel = (TextView) findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvSave = (TextView) findViewById(R.id.tvSave);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_item, menu);

        // return true so that the menu pop up is opened
        return true;
//        return super.onCreateOptionsMenu(menu);
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
        super.onBackPressed();
        AddVehiclesActivity.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("TTTT", "On touch..............");
        ((LinearLayout) inputLayoutRegistrationNo.getParent()).clearFocus();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
