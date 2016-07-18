package reader.parsers;

import java.util.Map;

class EnvParser implements PropertyParser {

    private Map<String, String> envMap;

    public EnvParser() {
        this.envMap = System.getenv();
    }

    @Override
    public String get(String property) {
        return envMap.get(property);
    }

    @Override
    public void reLoad() {
        envMap = System.getenv();
    }

}
