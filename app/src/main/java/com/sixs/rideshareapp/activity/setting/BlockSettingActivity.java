package com.sixs.rideshareapp.activity.setting;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.sixs.rideshareapp.Model.BlockCompaniesModel;
import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.adapter.BlockComapniesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BlockSettingActivity extends AppCompatActivity {

    String blockType;
    private RecyclerView myVehiclesRecyclerview;
    BlockComapniesAdapter myVehiclesAdapter;
    public ArrayList<BlockCompaniesModel> myVehiclesList = new ArrayList<BlockCompaniesModel>();
    TextInputLayout inputLayoutBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_setting_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        blockType = getIntent().getStringExtra("blockType");

        if(blockType.equalsIgnoreCase("BlockCompanies")) {
            toolbar.setTitle("Block Companies");
        }
        else if(blockType.equalsIgnoreCase("BlockPax")) {
            toolbar.setTitle("Block Pax");
        }
        else if(blockType.equalsIgnoreCase("PrefferedPax")) {
            toolbar.setTitle("Preffered Pax");
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setView();
    }

    private void setView() {
        inputLayoutBlock = (TextInputLayout) findViewById(R.id.inputLayoutBlock);
        if(blockType.equalsIgnoreCase("BlockCompanies")) {
            inputLayoutBlock.setHint("Enter Company Name");
        }
        else if(blockType.equalsIgnoreCase("BlockPax")) {
            inputLayoutBlock.setHint("Type");
        }
        else if(blockType.equalsIgnoreCase("PrefferedPax")) {
            inputLayoutBlock.setHint("Type");
        }
        inputLayoutBlock.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                BlockSettingActivity.this.myVehiclesRecyclerview.getFilter().filter(cs);
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myVehiclesAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        myVehiclesRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
//        myVehiclesRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        myVehiclesRecyclerview.setHasFixedSize(true);

        List<BlockCompaniesModel> list = new ArrayList<BlockCompaniesModel>();
        if(blockType.equalsIgnoreCase("BlockCompanies")) {

            for (int i = 0; i < 5; i++) {
                Log.v("TTT", "I = " + i);
                BlockCompaniesModel model = new BlockCompaniesModel();
                model.setId(String.valueOf(i+1));
                if(i==0)
                    model.setName("6S Solution Pvt. Ltd.");
                else if(i==1)
                    model.setName("Capgemini");
                else if(i==2)
                    model.setName("HCL Technologies");
                else if(i==3)
                    model.setName("Tata Communications");
                else if(i==4)
                    model.setName("MakeMyTrip.com");
                list.add(model);
            }
        }
        else if(blockType.equalsIgnoreCase("BlockPax")) {

            for (int i = 0; i < 4; i++) {
                Log.v("TTT", "I = " + i);
                BlockCompaniesModel model = new BlockCompaniesModel();
                model.setId(String.valueOf(i+1));
                if(i==0)
                    model.setName("Rashmi Narola");
                else if(i==1)
                    model.setName("Shine India");
                else if(i==2)
                    model.setName("Vishnu Purushothaman");
                else if(i==3)
                    model.setName("Pratiksha Khalasi");
                list.add(model);
            }
        }
        else if(blockType.equalsIgnoreCase("PrefferedPax")) {
            for (int i = 0; i < 4; i++) {
                Log.v("TTT", "I = " + i);
                BlockCompaniesModel model = new BlockCompaniesModel();
                model.setId(String.valueOf(i+1));
                if(i==0)
                    model.setName("Rashmi Narola");
                else if(i==1)
                    model.setName("Shine India");
                else if(i==2)
                    model.setName("Vishnu Purushothaman");
                else if(i==3)
                    model.setName("Pratiksha Khalasi");
                list.add(model);
            }
        }

        Log.v("TTT", "list size = " + list.size());
        myVehiclesList.addAll(list);

//        Collections.sort(myVehiclesList);

        Log.v("TTT", "myVehiclesList size = " + myVehiclesList.size());
        sortList(myVehiclesList);

        myVehiclesAdapter= new BlockComapniesAdapter(BlockSettingActivity.this, myVehiclesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myVehiclesRecyclerview.setLayoutManager(mLayoutManager);
        myVehiclesRecyclerview.setItemAnimator(new DefaultItemAnimator());

        myVehiclesRecyclerview.setAdapter(myVehiclesAdapter);
    }

    private void sortList(ArrayList<BlockCompaniesModel> myVehiclesList) {
        Collections.sort(myVehiclesList, new Comparator<BlockCompaniesModel>() {
            @Override
            public int compare(BlockCompaniesModel s1, BlockCompaniesModel s2) {
//                BlockCompaniesModel obj1 = s1;
//                BlockCompaniesModel obj2 = s2;
                Log.v("TTT","obj1.getName() = " + s1.getName() + "  " + s2.getName());
//                if(s1.getName() != null && s2.getName() != null)
                try {
                    return (s1.getName().compareTo(s2.getName()));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


                return 0;
            }
        });
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
        this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ((LinearLayout) inputLayoutBlock.getParent()).clearFocus();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
