package reader;

import annotation.Env;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class EnvInvocationHandler implements InvocationHandler {

    private final Map<Method, MethodHandler> methodHandlerCache = new HashMap<Method, MethodHandler>();

    EnvInvocationHandler(Class<?> aClass, PropertyParser parser) {
        Env env = aClass.getAnnotation(Env.class);
        EnvConfig envConfig = new EnvConfig(env);
        for (Method method : aClass.getDeclaredMethods()) {
            methodHandlerCache.put(method, new MethodHandler(method, envConfig, parser));
        }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return methodHandlerCache.get(method).getValue();
    }
}
