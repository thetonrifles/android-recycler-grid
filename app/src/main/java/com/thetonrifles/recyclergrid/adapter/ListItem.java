package com.thetonrifles.recyclergrid.adapter;

public class ListItem extends AbstractItem {

    public ListItem(String label) {
        super(label);
    }

    @Override
    public int getType() {
        return LIST_TYPE;
    }

}
