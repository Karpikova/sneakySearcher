package com.example.sneakysearch.result;

public class MistakeMy implements Mistake{ //собстно, наполнить ошибками при 10 попытках открыть финальную страницу
    //если ошибка возникла при попытках открытия страницы с 10 закупками, тогда ронять всё приложение
    private final String text;

    public MistakeMy(String text) {
        this.text = text;
    }

    @Override
    public String text() {
        return text;
    }
}
