package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.thetonrifles.recyclergrid.adapter.Adapter;
import com.thetonrifles.recyclergrid.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private List<ListItem> mAllItems;
    private List<ListItem> mFilteredItems;
    private String mSearchQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mSearchQuery = savedInstanceState.getString("query");
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.lst_items);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAllItems = new ArrayList<>();
        mAllItems.add(new ListItem("Francesco"));
        mAllItems.add(new ListItem("Daniele"));
        mAllItems.add(new ListItem("Kevin"));
        mAllItems.add(new ListItem("Radja"));
        mAllItems.add(new ListItem("Miralem"));
        mAllItems.add(new ListItem("Kostas"));
        mAllItems.add(new ListItem("Alessandro"));
        mAllItems.add(new ListItem("Diego"));
        mAllItems.add(new ListItem("Stephan"));
        mAllItems.add(new ListItem("Mohamed"));
        mAllItems.add(new ListItem("William"));
        mAllItems.add(new ListItem("Douglas"));
        mAllItems.add(new ListItem("Seydou"));

        mFilteredItems = new ArrayList<>();
        mFilteredItems.addAll(mAllItems);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new Adapter(getActivity(), mFilteredItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", mSearchQuery);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        mSearchView.setOnQueryTextListener(this);
        if (mSearchQuery != null) {
            mSearchView.setQuery(mSearchQuery, true);
        }
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mSearchQuery = query;
        mFilteredItems.clear();
        for (ListItem item : mAllItems) {
            if (item.getLabel().toLowerCase()
                    .contains(mSearchQuery.toLowerCase())) {
                mFilteredItems.add(item);
            }
        }
        mAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
