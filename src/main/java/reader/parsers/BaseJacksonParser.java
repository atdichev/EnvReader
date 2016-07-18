package reader.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reader.EnvException;

import java.io.File;
import java.io.IOException;

abstract class BaseJacksonParser implements PropertyParser {

    private final File file;
    private JsonNode node;

    public BaseJacksonParser(File file) {
        this.file = file;
        load();
    }

    private void load() {
        try {
            ObjectMapper mapper = getMapper();
            this.node = mapper.readTree(file);
            assert this.node != null;
        } catch (IOException e) {
            throw new EnvException(e);
        }
    }

    @Override
    public String get(String property) {
        return getProperty(node, property.split("\\."));
    }

    @Override
    public void reLoad() {
        load();
    }

    // For accessing nested properties like name.Firstname
    private String getProperty(JsonNode node, String[] props) {
        for (int i = 0; i < props.length - 1; ++i) {
            node = node.get(props[i]);
        }
        return node.get(props[props.length - 1]).asText();
    }

    abstract protected ObjectMapper getMapper();

}


