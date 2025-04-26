package com.azsoftware.envreader.reader.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;

class YamlParser extends BaseJacksonParser {

    public YamlParser(File file) {
        super(file);
    }

    @Override
    protected ObjectMapper getMapper() {
        return new YAMLMapper();
    }

}
