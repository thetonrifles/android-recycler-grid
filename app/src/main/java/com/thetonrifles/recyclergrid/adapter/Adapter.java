package com.thetonrifles.recyclergrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclergrid.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {

    protected static class ListViewHolder extends RecyclerView.ViewHolder {

        TextView txt_label;

        public ListViewHolder(View itemView) {
            super(itemView);
            txt_label = (TextView) itemView.findViewById(R.id.txt_label);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<ListItem> mItems;

    public Adapter(Context context, List<ListItem> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        holder.txt_label.setText(item.getLabel());
    }

}
