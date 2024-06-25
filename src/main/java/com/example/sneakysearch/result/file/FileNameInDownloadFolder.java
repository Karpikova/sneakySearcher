package com.example.sneakysearch.result.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileNameInDownloadFolder implements FileName {
    private final String phrase;

    public FileNameInDownloadFolder(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String value() {
        final String home = System.getProperty("user.home");
        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return home + "/Downloads/" + phrase + "_" + timeStamp + ".xls";
    }
}
