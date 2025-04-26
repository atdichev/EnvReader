package com.azsoftware.envreader.reader.parsers;

public interface PropertyParser {

    String get(String property);

    void reLoad();

}
