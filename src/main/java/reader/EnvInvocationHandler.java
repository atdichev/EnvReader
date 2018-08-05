package reader;

import annotation.Env;
import reader.parsers.ParserFactory;
import reader.parsers.PropertyParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class EnvInvocationHandler implements InvocationHandler {

    private final Map<Method, MethodHandler> methodHandlerCache = new HashMap<Method, MethodHandler>();

    EnvInvocationHandler(Class<?> aClass) {
        Env env = aClass.getAnnotation(Env.class);
        Type type = env.type();
        URL url = aClass.getResource(env.file());
        if (type != Type.SYSTEM_ENV && url == null) {
            throw new EnvException(new FileNotFoundException());
        }
        File file = (type != Type.SYSTEM_ENV) ? new File(url.getFile()) : null;
        PropertyParser parser = ParserFactory.createParser(type, file);
        EnvConfig envConfig = new EnvConfig(env);
        for (Method method : aClass.getMethods()) {
            methodHandlerCache.put(method, new MethodHandler(method, envConfig, parser));
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return methodHandlerCache.get(method).getValue();
    }
}
