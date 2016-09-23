package com.sixs.rideshareapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.sixs.rideshareapp.R;

/**
 * Created by RASHMI on 22-09-2016.
 */
public class MyRatingFragment  extends Fragment {

    private RatingBar ratingBar;

    public MyRatingFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_rating_view, container, false);

        setVIew(rootView);

        return rootView;
    }

    private void setVIew(View view) {
        Log.v("TTT", "Package NBame = " + getActivity().getApplicationContext().getPackageName());
//        Uri marketUri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
//        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//        startActivity(marketIntent);
    }
}
