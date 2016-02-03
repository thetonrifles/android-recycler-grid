package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thetonrifles.recyclergrid.adapter.AbstractItem;
import com.thetonrifles.recyclergrid.adapter.Adapter;
import com.thetonrifles.recyclergrid.adapter.GridItem;
import com.thetonrifles.recyclergrid.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<AbstractItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<>();
        mItems.add(new GridItem("grid item 1"));
        mItems.add(new GridItem("grid item 2"));
        mItems.add(new GridItem("grid item 3"));
        mItems.add(new GridItem("grid item 4"));
        mItems.add(new ListItem("list item 1"));
        mItems.add(new ListItem("list item 2"));
        mItems.add(new ListItem("list item 3"));
        mItems.add(new ListItem("list item 4"));

        // building layout manager... this is the most important part
        // we define a grid view with 2 columns
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                AbstractItem item = mItems.get(position);
                switch (item.getType()) {
                    case AbstractItem.GRID_TYPE:
                        // grid items to take 1 column
                        return 1;
                    default:
                        // list items to take 2 columns
                        return 2;
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        Adapter adapter = new Adapter(this, mItems);
        recyclerView.setAdapter(adapter);
    }

}
