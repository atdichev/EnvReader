package com.azsoftware.envreader.reader.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.azsoftware.envreader.reader.EnvException;

class TextParser implements PropertyParser {
  @Override
  public String get(String fileName) {
    URL url = Optional.ofNullable(getClass().getResource(fileName))
      .orElseThrow(() -> new EnvException(new FileNotFoundException("File not found: " + fileName)));

    try {
      Path path = Paths.get(url.toURI());
      return Files.readString(path); // More concise than `new String(Files.readAllBytes(path))`
    } catch (IOException | URISyntaxException e) {
      throw new EnvException("Error reading file: " + fileName + "\n" + e.getMessage());
    }
  }

  @Override
  public void reLoad() {
    // Add logic here if needed in future
  }
}
