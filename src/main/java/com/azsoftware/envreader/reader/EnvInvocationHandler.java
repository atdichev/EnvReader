package com.azsoftware.envreader.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.azsoftware.envreader.annotation.Env;
import com.azsoftware.envreader.reader.parsers.ParserFactory;
import com.azsoftware.envreader.reader.parsers.PropertyParser;

class EnvInvocationHandler implements InvocationHandler {

    private final Map<Method, MethodHandler> methodHandlerCache = new HashMap<Method, MethodHandler>();

    EnvInvocationHandler(Class<?> aClass) {
        Env env = aClass.getAnnotation(Env.class);
        Type type = env.type();
        URL url = aClass.getResource(env.file());
        if (type != Type.SYSTEM_ENV && url == null) {
            throw new EnvException(new FileNotFoundException());
        }
        File file;
		try {
			file = (type != Type.SYSTEM_ENV) ? Paths.get(url.toURI()).toFile() : null;
		} catch (URISyntaxException e) {
			throw new EnvException(e);
		}
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
