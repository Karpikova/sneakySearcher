package com.example.sneakysearch.excel;

import com.example.sneakysearch.SneakySearchException;

import java.util.List;

public interface Headers {
    List<Header> value() throws SneakySearchException;
}
