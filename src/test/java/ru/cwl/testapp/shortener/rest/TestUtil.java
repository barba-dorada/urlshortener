package ru.cwl.testapp.shortener.rest;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by vadim.tishenko
 * on 22.05.2017 22:02.
 */

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}