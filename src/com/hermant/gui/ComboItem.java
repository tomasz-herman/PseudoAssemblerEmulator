package com.hermant.gui;


class ComboItem{
    private final int value;
    private final String label;

    ComboItem(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }
}
