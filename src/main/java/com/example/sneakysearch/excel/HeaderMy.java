package com.example.sneakysearch.excel;

public class HeaderMy implements Header {
    private final String caption;
    private final int size;

    public HeaderMy(String caption, int size) {
        this.caption = caption;
        this.size = size;
    }

    @Override
    public String caption() {
        return caption;
    }

    @Override
    public int size() {
        return size;
    }
}
