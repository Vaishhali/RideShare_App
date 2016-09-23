package com.sixs.rideshareapp.Model;

import java.util.ArrayList;

/**
 * Created by RASHMI on 15-09-2016.
 */
public class MobileNumberModel {

    boolean success;
    boolean OTPExpired;
    String msg;
    DataModel Data;


    public boolean isOTPExpired() {
        return OTPExpired;
    }

    public void setOTPExpired(boolean OTPExpired) {
        this.OTPExpired = OTPExpired;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataModel getData() {
        return Data;
    }

    public void setData(DataModel data) {
        Data = data;
    }

    public class DataModel {

        ArrayList<UserDetailModel> userdetails;
        String SMS_Sender_Response;
        String OTP;


        public ArrayList<UserDetailModel> getUserdetails() {
            return userdetails;
        }

        public void setUserdetails(ArrayList<UserDetailModel> userdetails) {
            this.userdetails = userdetails;
        }

        public String getSMS_Sender_Response() {
            return SMS_Sender_Response;
        }


        public String isSMS_Sender_Response() {
            return SMS_Sender_Response;
        }

        public void setSMS_Sender_Response(String SMS_Sender_Response) {
            this.SMS_Sender_Response = SMS_Sender_Response;
        }

        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
        }
    }

    public class UserDetailModel {

    }
}

