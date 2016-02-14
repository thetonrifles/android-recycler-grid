package com.thetonrifles.recyclergrid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclergrid.R;

import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.GridViewHolder> {

    protected static class GridViewHolder extends RecyclerView.ViewHolder {

        TextView txt_label;

        public GridViewHolder(View itemView) {
            super(itemView);
            txt_label = (TextView) itemView.findViewById(R.id.txt_label);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<GridItem> mItems;

    public Adapter(Context context, List<GridItem> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_grid_item, parent, false);
        itemView.setBackgroundColor(getRandomColor());
        return new GridViewHolder(itemView);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        GridItem item = mItems.get(position);
        holder.txt_label.setText(item.getLabel());
    }

}
