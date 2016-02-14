package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        for (int i=0; i<50; i++) {
            mItems.add(new GridItem(String.valueOf(i+1)));
        }

        MyLayoutManager manager = new MyLayoutManager(this, 3);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);
        Adapter adapter = new Adapter(this, mItems);
        recyclerView.setAdapter(adapter);
    }

}
