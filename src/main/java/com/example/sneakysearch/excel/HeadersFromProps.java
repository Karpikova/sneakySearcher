package com.example.sneakysearch.excel;

import com.example.sneakysearch.SneakySearchException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class HeadersFromProps implements Headers {
    private final String propNameOfHeaders;
    private final String appFile;

    public HeadersFromProps(String propNameOfHeaders, String appFile) {
        this.propNameOfHeaders = propNameOfHeaders;
        this.appFile = appFile;
    }

    @Override
    public List<Header> value() throws SneakySearchException {
        final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        final String file = rootPath + appFile;

        final Properties appProps = new Properties();
        try (final FileInputStream fis = new FileInputStream(file)) {
            appProps.load(fis);
        } catch (IOException e) {
            throw new SneakySearchException(e);
        }

        final String[] headers = appProps.getProperty(propNameOfHeaders).split(";");
        return Arrays.stream(headers).
                map(q -> (Header) new HeaderMy(q.split(",")[0], Integer.parseInt(q.split(",")[1]))).
                toList();
    }
}
