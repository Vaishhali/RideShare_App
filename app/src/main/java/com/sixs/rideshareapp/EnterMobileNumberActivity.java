package com.sixs.rideshareapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sixs.rideshareapp.Model.MobileNumberModel;
import com.sixs.rideshareapp.Util.SharedPreference;
import com.sixs.rideshareapp.Util.Utils;
import com.sixs.rideshareapp.activity.RegisterSuccessActivity;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnterMobileNumberActivity extends AppCompatActivity {

    AppCompatButton btnMobileNumber;
    TextInputLayout input_layout_password;

    private RequestQueue requestQueue;
    String preferenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_mobile_number_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Sign In or Register");
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        btnMobileNumber = (AppCompatButton) findViewById(R.id.btnMobileNumber);

        try {
            preferenceNumber  = SharedPreference.getMobileNumber(EnterMobileNumberActivity.this, null);
            Log.v("TTT", "SharedPreference.getMobileNumber = " + SharedPreference.getMobileNumber(EnterMobileNumberActivity.this, null));
            if(preferenceNumber != null && !preferenceNumber.isEmpty()) {
                input_layout_password.getEditText().append(preferenceNumber.trim());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        btnMobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValid();
            }
        });
    }

    private void checkValid() {
        if(TextUtils.isEmpty(input_layout_password.getEditText().getText().toString())) {
            input_layout_password.setErrorEnabled(true);
            input_layout_password.setError(getString(R.string.phone_number_validation));
        }
        else if(input_layout_password.getEditText().getText().toString().length() < 10) {
            input_layout_password.setErrorEnabled(true);
            input_layout_password.setError(getString(R.string.phone_number_digit_validation));
        }
        else {
            input_layout_password.setErrorEnabled(false);
            new RegisterMobileNumberAsync(input_layout_password.getEditText().getText().toString().trim()).execute();
//            Intent myIntent = new Intent(EnterMobileNumberActivity.this, EnterOTPActivity.class);
//            EnterMobileNumberActivity.this.startActivity(myIntent);
//            EnterMobileNumberActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
        }
    }

    private class RegisterMobileNumberAsync extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        String mobileNumber;

        public RegisterMobileNumberAsync(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(EnterMobileNumberActivity.this);
            dialog.setMessage("Please wait..");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.v("RRR", "do in background ==== " + Utils.API_URL+Utils.MOBILE_NUMBER_REGISTER_URL);
//            postData(params[0]);
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Utils.API_URL+Utils.MOBILE_NUMBER_REGISTER_URL);//"http://somewebsite.com/receiver.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("mobilenumber", mobileNumber));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                String responseStr = EntityUtils.toString(response.getEntity());
                Log.v("TTT", "response = " + response.toString());
                return  responseStr;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result){
            Log.v("TTT", "Result = " + result);
            try {
                dialog.dismiss();
                if(Utils.isNetworkAvailable(EnterMobileNumberActivity.this)) {
                    if(result != null) {
                        Gson gson = new Gson();
                        MobileNumberModel mobileNumberModel = gson.fromJson(result, MobileNumberModel.class);

                        if(mobileNumberModel.isSuccess()) {
                            if(preferenceNumber != null && preferenceNumber.equalsIgnoreCase(mobileNumber)) {
                                Intent intent = new Intent(EnterMobileNumberActivity.this, RegisterSuccessActivity.class);
                                intent.putExtra("isSuccess", mobileNumberModel.isSuccess());
                                intent.putExtra("msg", mobileNumberModel.getMsg());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                EnterMobileNumberActivity.this.startActivity(intent);
                                EnterMobileNumberActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                            }
                            else {
                                Intent myIntent = new Intent(EnterMobileNumberActivity.this, EnterOTPActivity.class);
                                myIntent.putExtra("mobileNumber",mobileNumber);
                                myIntent.putExtra("otpResponse", result);
                                EnterMobileNumberActivity.this.startActivity(myIntent);
                                EnterMobileNumberActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                            }
                        }
                        else {
                            new AlertDialog.Builder(EnterMobileNumberActivity.this)
                                    .setMessage(mobileNumberModel.getMsg())
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }
                    else {
                        new AlertDialog.Builder(EnterMobileNumberActivity.this)
                            .setMessage("Result : " + result)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                }
                            })
                        .show();
                    }
                }
                else {
                    new AlertDialog.Builder(EnterMobileNumberActivity.this)
//                            .setTitle("Delete entry")
                    .setMessage("Please check your internet connection.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            dialog.dismiss();
                        }
                    })
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ((LinearLayout) input_layout_password.getParent()).clearFocus();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
