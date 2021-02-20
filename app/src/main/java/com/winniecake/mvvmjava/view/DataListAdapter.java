package com.winniecake.mvvmjava.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winniecake.mvvmjava.R;
import com.winniecake.mvvmjava.model.MyData;

import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<MyData> mDataList;

    public DataListAdapter(Context context, ArrayList<MyData> dataList) {
        super();
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public DataListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataListAdapter.ViewHolder holder, int position) {
        holder.mTextNo.setText(mDataList.get(position).no);
        holder.mTextName.setText(mDataList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void updateList(ArrayList<MyData> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextNo;
        TextView mTextName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextNo = itemView.findViewById(R.id.no);
            mTextName = itemView.findViewById(R.id.name);
        }
    }
}
