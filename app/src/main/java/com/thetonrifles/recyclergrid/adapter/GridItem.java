package com.thetonrifles.recyclergrid.adapter;

public class GridItem extends AbstractItem {

    public GridItem(String label) {
        super(label);
    }

    @Override
    public int getType() {
        return GRID_TYPE;
    }

}
