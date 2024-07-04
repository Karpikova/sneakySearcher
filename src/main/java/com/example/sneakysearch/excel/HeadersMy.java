package com.example.sneakysearch.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class HeadersMy implements Headers {
    private final List<String> headers;

    public HeadersMy() {
        headers = new ArrayList<>(Arrays.asList("Что искали",
                "Ссылка",
                "Номер",
                "Объект закупки",
                "Клиент",
                "Дата обновления"
                ));
    }

    @Override
    public List<String> value() {
        return headers;
    }
}
