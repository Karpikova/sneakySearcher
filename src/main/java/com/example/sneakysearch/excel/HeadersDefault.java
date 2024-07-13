package com.example.sneakysearch.excel;

import com.example.sneakysearch.SneakySearchException;

import java.util.Arrays;
import java.util.List;

public class HeadersDefault implements Headers {
    @Override
    public List<Header> value() throws SneakySearchException {
        Header search = new HeaderMy("Искали", 5000);
        Header link = new HeaderMy("Ссылка", 23000);
        Header number = new HeaderMy("Номер", 4800);
        Header object = new HeaderMy("Объект", 7000);
        Header client = new HeaderMy("Клиент", 9500);
        Header updated = new HeaderMy("Обновлено", 2750);
        List<Header> headers = Arrays.asList(search, link, number, object, client, updated);
        return headers;
    }
}
