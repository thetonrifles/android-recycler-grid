package com.thetonrifles.recyclergrid;

public class EmptyItem extends AbstractItem {

    public EmptyItem(String label) {
        super(label);
    }

    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
