package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thetonrifles.recyclergrid.adapter.Adapter;
import com.thetonrifles.recyclergrid.adapter.GridItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<GridItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<>();
        mItems.add(new GridItem("Q"));
        mItems.add(new GridItem("W"));
        mItems.add(new GridItem("E"));
        mItems.add(new GridItem("R"));
        mItems.add(new GridItem("T"));
        mItems.add(new GridItem("Y"));
        mItems.add(new GridItem("U"));
        mItems.add(new GridItem("I"));
        mItems.add(new GridItem("O"));
        mItems.add(new GridItem("P"));
        mItems.add(new GridItem("A"));
        mItems.add(new GridItem("S"));
        mItems.add(new GridItem("D"));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mItems.size() % 2 == 0) {
                    return 1;
                } else {
                    return (position == mItems.size() - 1) ? 2 : 1;
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        Adapter adapter = new Adapter(this, mItems);
        recyclerView.setAdapter(adapter);
    }

}
