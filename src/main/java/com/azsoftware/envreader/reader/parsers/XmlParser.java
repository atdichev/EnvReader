package com.azsoftware.envreader.reader.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

class XmlParser extends BaseJacksonParser {

    public XmlParser(File file) {
        super(file);
    }

    @Override
    protected ObjectMapper getMapper() {
        return new XmlMapper();
    }

}
