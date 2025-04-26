package com.azsoftware.envreader.reader;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

final class Utils {

    private static final Set<Class<?>> validReturnTypes;

    static {
        validReturnTypes = new HashSet<Class<?>>();
        validReturnTypes.add(int.class);
        validReturnTypes.add(Integer.class);
        validReturnTypes.add(double.class);
        validReturnTypes.add(Double.class);
        validReturnTypes.add(float.class);
        validReturnTypes.add(Float.class);
        validReturnTypes.add(String.class);
        validReturnTypes.add(boolean.class);
        validReturnTypes.add(Boolean.class);
    }

    public static boolean validReturnType(Class<?> aClass) {
        return validReturnTypes.contains(aClass);
    }

    private static boolean equalsAny(final Class<?> testClass, final Class<?>... classes) {
        for (Class<?> aClass : classes) {
            if (aClass.equals(testClass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existsFile(File file) {
        return file.exists() && !file.isDirectory();
    }

    @SuppressWarnings("all")
    public static Object convert(final String s, final Class<?> aClass) {
        if (equalsAny(aClass, double.class, Double.class))
            return Double.valueOf(s).doubleValue();
        else if (equalsAny(aClass, float.class, Float.class))
            return Double.valueOf(s).floatValue();
        else if (equalsAny(aClass, int.class, Integer.class))
            return Double.valueOf(s).intValue();
        else if (equalsAny(aClass, boolean.class, Boolean.class)) {
            return s.equalsIgnoreCase("true");
        }
        return s;
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

}
