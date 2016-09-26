package com.sixs.rideshareapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.sixs.rideshareapp.Model.BlockCompaniesModel;
import com.sixs.rideshareapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RASHMI on 20-09-2016.
 */
public class BlockComapniesAdapter extends RecyclerView.Adapter<BlockComapniesAdapter.BlockComapniesHolder> implements Filterable {

    private ArrayList<BlockCompaniesModel> myVehiclesList;
    Activity mContext;
    private CustomFilter mFilter;
    private List<BlockCompaniesModel> filteredList = new ArrayList<BlockCompaniesModel>();

    public BlockComapniesAdapter(Activity context, ArrayList<BlockCompaniesModel> myVehiclesList) {
        this.mContext = context;
        this.myVehiclesList = myVehiclesList;
        this.filteredList.addAll(myVehiclesList);

        mFilter = new CustomFilter(BlockComapniesAdapter.this);
    }

    @Override
    public BlockComapniesAdapter.BlockComapniesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_comapnies_row_view, parent, false);

        return new BlockComapniesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BlockComapniesAdapter.BlockComapniesHolder holder, final int position) {
        Log.v("TTT","filteredList size = " + filteredList.size() + " " + myVehiclesList.size());
        if(filteredList.size() == 0) {
            holder.switchCompanyName.setVisibility(View.GONE);
            holder.tvNoResult.setVisibility(View.VISIBLE);
        }
        else {
            holder.switchCompanyName.setVisibility(View.VISIBLE);
            holder.tvNoResult.setVisibility(View.GONE);
            holder.switchCompanyName.setText(filteredList.get(position).getName());
        }

    }

    @Override
    public Filter getFilter() {
        Log.v("TTT", "getFilter......");
        return mFilter;
    }

    @Override
    public int getItemCount() {
        if(filteredList.size() == 0) {
            return  1;
        }
        else {
            return filteredList.size();
        }
    }

    public class BlockComapniesHolder extends RecyclerView.ViewHolder {
        SwitchCompat switchCompanyName;
        public final View mView;
        public TextView tvNoResult;
//        LinearLayout layoutSubHeader;
//        RelativeLayout layoutHeader;
//        ImageView ivMenu;

        public BlockComapniesHolder(View view) {
            super(view);
            mView = view;
            switchCompanyName = (SwitchCompat) view.findViewById(R.id.switchCompanyName);
//            layoutHeader = (RelativeLayout) view.findViewById(R.id.layoutHeader);
//            layoutSubHeader = (LinearLayout) view.findViewById(R.id.layoutSubHeader);
            tvNoResult = (TextView) view.findViewById(R.id.tvNoResult);
//            ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public class CustomFilter extends Filter {
        private BlockComapniesAdapter mAdapter;
        private CustomFilter(BlockComapniesAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            Log.v("TTT", "myVehiclesList=  " + myVehiclesList.size());
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(myVehiclesList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final BlockCompaniesModel mWords : myVehiclesList) {
                    if (mWords.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(mWords);
                    }
                }
            }
            System.out.println("Count Number " + filteredList.size());
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            System.out.println("Count Number 2 " + ((List<BlockCompaniesModel>) results.values).size());
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
