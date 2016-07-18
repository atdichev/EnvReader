package reader.parsers;

import reader.EnvException;
import reader.Type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ParserFactory {
    public static PropertyParser createParser(final Type type, final File file) {
        switch (type) {
            case JSON:
                return new JsonParser(file);
            case XML:
                return new XmlParser(file);
            case YAML:
                return new YamlParser(file);
            case SYSTEM_ENV:
                return new EnvParser();
            case PROPERTIES:
                try {
                    return new JavaPropertiesParser(new FileInputStream(file));
                } catch (FileNotFoundException e) {
                    throw new EnvException(e);
                }
        }
        //irrelevant, all types have a corresponding parser
        return null;
    }
}
