package com.azsoftware.envreader.reader.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

class JsonParser extends BaseJacksonParser {

    public JsonParser(File file) {
        super(file);
    }

    @Override
    protected ObjectMapper getMapper() {
        return new ObjectMapper();
    }

}
