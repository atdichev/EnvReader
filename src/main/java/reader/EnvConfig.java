package reader;

import annotation.Env;

// Useful when more config properties added like ( deployPath -> DEPLOY_PATH)
class EnvConfig {
    private final String prefix;

    public EnvConfig(Env env) {
        this.prefix = env.Prefix();
    }

    public String getConfiguredKey(String key) {
        if (prefix.isEmpty())
            return key;
        return prefix + key;
    }
}
