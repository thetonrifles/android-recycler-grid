package com.thetonrifles.recyclergrid.adapter;

public abstract class AbstractItem {

    public static final int GRID_TYPE = 0;
    public static final int LIST_TYPE = 1;

    private String label;

    public AbstractItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    abstract public int getType();

}
