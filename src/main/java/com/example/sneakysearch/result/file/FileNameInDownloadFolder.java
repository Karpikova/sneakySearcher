package com.example.sneakysearch.result.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileNameInDownloadFolder implements FileName {
    private final String word;

    public FileNameInDownloadFolder(String word) {
        this.word = word;
    }

    @Override
    public String value() {
        final String home = System.getProperty("user.home");
        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return home + "/Downloads/" + word + "_" + timeStamp + ".xls";
    }
}
