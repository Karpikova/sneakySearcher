package com.example.sneakysearch;

public final class SneakySearchException extends Exception {
    public SneakySearchException(String url) {
        super(url);
    }
    public SneakySearchException(Exception e) {
        super(e);
    }
}
