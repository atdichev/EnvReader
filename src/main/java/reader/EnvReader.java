package reader;

import annotation.Env;

import java.lang.reflect.Proxy;


public class EnvReader {

    private static final PropertyParser envParser = new PropertyParser() {
        @Override
        public Object get(String property) {
            return System.getenv(property);
        }
    };

    private static void checkValidClass(Class<?> aClass) {
        if (!aClass.isInterface())
            throw new EnvException("Only Interfaces can have Env Annotation");
        if (!aClass.isAnnotationPresent(Env.class))
            throw new EnvException("Interface should have Env Annotation");
    }

    @SuppressWarnings("unchecked")
    public static <T> T createReader(Class<T> aClass, PropertyParser parser) {
        checkValidClass(aClass);
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass}, new EnvInvocationHandler(aClass, parser));
    }

    @SuppressWarnings("unchecked")
    public static <T> T createReader(Class<T> aClass) {
        return createReader(aClass, envParser);
    }
}