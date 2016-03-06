package reader;

import java.util.HashSet;
import java.util.Set;

class Utils {

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
            if (aClass.equals(testClass))
                return true;
        }
        return false;
    }

    @SuppressWarnings("all")
    public static Object convert(final Object o, final Class<?> aClass) {
        String stringVal = o.toString();
        if (equalsAny(aClass, double.class, Double.class))
            return Double.valueOf(stringVal).doubleValue();
        else if (equalsAny(aClass, float.class, Float.class))
            return Double.valueOf(stringVal).floatValue();
        else if (equalsAny(aClass, int.class, Integer.class))
            return Double.valueOf(stringVal).intValue();
        else if (equalsAny(aClass, boolean.class, Boolean.class)) {
            return stringVal.equalsIgnoreCase("true");
        }
        return stringVal;
    }


}
