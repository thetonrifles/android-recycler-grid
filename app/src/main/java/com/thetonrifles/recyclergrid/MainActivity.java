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
        mItems.add(new GridItem("grid item 1"));
        mItems.add(new GridItem("grid item 2"));
        mItems.add(new GridItem("grid item 3"));
        mItems.add(new GridItem("grid item 4"));
        mItems.add(new GridItem("grid item 5"));

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
