package com.azsoftware.envreader.reader;

import java.lang.reflect.Proxy;

import com.azsoftware.envreader.annotation.Env;


public class EnvReader {

    private static void checkValidClass(Class<?> aClass) {
        Utils.requireNonNull(aClass, "aClass cannot be null");
        if (!aClass.isInterface()) {
            throw new EnvException("Only Interfaces can have Env Annotation");
        }
        if (!aClass.isAnnotationPresent(Env.class)) {
            throw new EnvException("Interface should have Env Annotation");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T createReader(Class<T> aClass) {
        checkValidClass(aClass);
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), new Class[] { aClass }, new EnvInvocationHandler(aClass));
    }

}