package com.example.sneakysearch;

import java.util.Set;

public class WrittenToFileMy implements WrittenToFile {
    private FoundFromWeb foundFromWeb;

    public WrittenToFileMy(FoundFromWeb foundFromWeb) {
        this.foundFromWeb = foundFromWeb;
    }

    public WrittenToFileMy(String word) {
        this(new FoundFromWebMy(word));
    }

    @Override
    public void writeToFile() {
        Set<String> links = foundFromWeb.foundFromWeb();
        System.out.println(links);
    }
}
