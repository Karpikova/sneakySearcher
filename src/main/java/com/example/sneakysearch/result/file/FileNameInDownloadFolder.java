package com.example.sneakysearch.result.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileNameInDownloadFolder implements FileName {
    private final String phrase;
    private final static String SYS_PROPERTY = "user.home";
    private final static String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    private final static String EXTENSION = ".xlsx";
    private final static String FOLDER_NAME = "/Downloads/";

    public FileNameInDownloadFolder(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String value() {
        final String home = System.getProperty(SYS_PROPERTY);
        final String timeStamp = new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
        return home + FOLDER_NAME + phrase + "_" + timeStamp + EXTENSION;
    }
}
