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

public class EnterOTPActivity extends AppCompatActivity {

    AppCompatButton btnOTP;
    TextInputLayout input_layout_otp;
    MobileNumberModel mobileNumberModel;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_otp_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("OTP Verification");
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        mobileNumber = getIntent().getStringExtra("mobileNumber");
        String mobileNumberResponse = getIntent().getStringExtra("otpResponse");
        Log.v("TTT", "In EnterOTPActivity  Otp data = " + mobileNumberResponse + "\nmobileNumber = " + mobileNumber);

        Gson gson = new Gson();
        mobileNumberModel = gson.fromJson(mobileNumberResponse, MobileNumberModel.class);

        if(mobileNumberModel != null) {
            Log.v("TTT", "OTP no =  " + mobileNumberModel.getData().getOTP());
        }

        input_layout_otp = (TextInputLayout) findViewById(R.id.input_layout_otp);
        input_layout_otp.getEditText().setText(mobileNumberModel.getData().getOTP());
        btnOTP = (AppCompatButton) findViewById(R.id.btnOTP);
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValid();
            }
        });
    }

    private void checkValid() {
        if(TextUtils.isEmpty(input_layout_otp.getEditText().getText().toString())) {
            input_layout_otp.setErrorEnabled(true);
            input_layout_otp.setError(getString(R.string.otp_validation));
        }
        else if(input_layout_otp.getEditText().getText().toString().length() < 4) {
            input_layout_otp.setErrorEnabled(true);
            input_layout_otp.setError(getString(R.string.otp_digit_validation));
        }
        else {
            input_layout_otp.setErrorEnabled(false);
            new RegisterOTPAsync(mobileNumber, input_layout_otp.getEditText().getText().toString().trim()).execute();
        }
    }

    private class RegisterOTPAsync extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;
        String mobileNumber, OTP;

        public RegisterOTPAsync(String mobileNumber, String OTP) {
            this.mobileNumber = mobileNumber;
            this.OTP = OTP;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(EnterOTPActivity.this);
            dialog.setMessage("Please wait..");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.v("RRR", "RegisterOTPAsync do in background ==== " + Utils.API_URL+Utils.OTP_REGISTER_URL);
//            postData(params[0]);
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Utils.API_URL+Utils.OTP_REGISTER_URL);//"http://somewebsite.com/receiver.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("mobilenumber", mobileNumber));
                nameValuePairs.add(new BasicNameValuePair("gcmregisterationid", getResources().getString(R.string.sender_id)));
                nameValuePairs.add(new BasicNameValuePair("imeinumber", mobileNumber));
                nameValuePairs.add(new BasicNameValuePair("otp", OTP));
                nameValuePairs.add(new BasicNameValuePair("geocoords", "{ \"latitude\":8.5207295, \"longitude\":76.9422873}"));

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
                if(Utils.isNetworkAvailable(EnterOTPActivity.this)) {
                    if(result != null) {
                        Gson gson = new Gson();
                        MobileNumberModel mobileNumberModel = gson.fromJson(result, MobileNumberModel.class);
//                        if(mobileNumberModel.isSuccess()) {
                            Intent intent = new Intent(EnterOTPActivity.this, RegisterSuccessActivity.class);
//                            intent.putExtra("otpResponse", result);
                            intent.putExtra("isSuccess", mobileNumberModel.isSuccess());
                            intent.putExtra("msg", mobileNumberModel.getMsg());
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            EnterOTPActivity.this.startActivity(intent);
                        EnterOTPActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);

                        if(mobileNumberModel.isSuccess()) {
                            SharedPreference.putMobileNumber(EnterOTPActivity.this, mobileNumber);
                        }
//                            EnterOTPActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//                            EnterOTPActivity.this.finish();
//                        }
//                        else {
//                            new AlertDialog.Builder(EnterOTPActivity.this)
//                                    .setMessage(mobileNumberModel.getMsg())
//                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            // continue with delete
//                                            dialog.dismiss();
//                                        }
//                                    })
//                                    .show();
//                        }
                    }
                    else {
                        new AlertDialog.Builder(EnterOTPActivity.this)
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
                    new AlertDialog.Builder(EnterOTPActivity.this)
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
        ((LinearLayout) input_layout_otp.getParent()).clearFocus();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
