package com.thetonrifles.recyclergrid.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thetonrifles.recyclergrid.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.GridViewHolder> {

    protected class GridViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView txt_label;
        ImageView img_banner;

        public GridViewHolder(View itemView, int width) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card);
            txt_label = (TextView) itemView.findViewById(R.id.tvCard);
            img_banner = (ImageView) itemView.findViewById(R.id.ivCard);
            card.setMinimumWidth(width);
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
            // work here if you need to control height of your items
            // keep in mind that parent is RecyclerView in this case
            int width = parent.getMeasuredWidth() / 2;
            return new GridViewHolder(itemView, width);
    }

    @Override
    public void onBindViewHolder(GridViewHolder viewHolder, int position) {
            GridItem item = mItems.get(position);
            viewHolder.txt_label.setText(item.getLabel());
    }

}
