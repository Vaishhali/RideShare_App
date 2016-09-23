package com.sixs.rideshareapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sixs.rideshareapp.R;
import com.sixs.rideshareapp.activity.setting.BlockSettingActivity;
import com.sixs.rideshareapp.activity.setting.GenderSettingActivity;

/**
 * Created by RASHMI on 15-09-2016.
 */
public class SettingsFragment extends Fragment {

    RelativeLayout layoutGender, layoutBlockCompanies, layoutBlockPax, layoutPrefferedPax;//, layoutMyScore, layoutMyRating, layoutChargeCalculation;

    public SettingsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting_view, container, false);
        setVIew(rootView);
        return rootView;
    }

    private void setVIew(View view) {
        layoutGender = (RelativeLayout) view.findViewById(R.id.layoutGender);
        layoutBlockCompanies = (RelativeLayout) view.findViewById(R.id.layoutBlockCompanies);
        layoutBlockPax = (RelativeLayout) view.findViewById(R.id.layoutBlockPax);
        layoutPrefferedPax = (RelativeLayout) view.findViewById(R.id.layoutPrefferedPax);
//        layoutMyScore = (RelativeLayout) view.findViewById(R.id.layoutMyScore);
//        layoutMyRating = (RelativeLayout) view.findViewById(R.id.layoutMyRating);
//        layoutChargeCalculation = (RelativeLayout) view.findViewById(R.id.layoutChargeCalculation);

        layoutGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), GenderSettingActivity.class);
                getActivity().startActivity(myIntent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
        layoutBlockCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), BlockSettingActivity.class);
                myIntent.putExtra("blockType", "BlockCompanies");
                getActivity().startActivity(myIntent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
        layoutBlockPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), BlockSettingActivity.class);
                myIntent.putExtra("blockType", "BlockPax");
                getActivity().startActivity(myIntent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
        layoutPrefferedPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), BlockSettingActivity.class);
                myIntent.putExtra("blockType", "PrefferedPax");
                getActivity().startActivity(myIntent);
                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
            }
        });
//        layoutMyScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(getActivity(), MyScoreActivity.class);
//                getActivity().startActivity(myIntent);
//                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//            }
//        });
//        layoutMyRating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(getActivity(), MyRatingActivity.class);
//                getActivity().startActivity(myIntent);
//                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//            }
//        });
//        layoutChargeCalculation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(getActivity(), ChargeCalculationActivity.class);
//                getActivity().startActivity(myIntent);
//                getActivity().overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
//            }
//        });

    }
}
