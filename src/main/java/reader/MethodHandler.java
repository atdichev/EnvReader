package reader;

import annotation.Bind;
import annotation.Property;

import java.lang.reflect.Method;

class MethodHandler {

    private final Class<?> returnType;
    private final boolean isBound;
    private final Object initialValue;
    private final String key;
    private final PropertyParser parser;

    public MethodHandler(Method method, EnvConfig envConfig, PropertyParser parser) {

        this.isBound = method.isAnnotationPresent(Bind.class);
        this.returnType = method.getReturnType();
        this.key = envConfig.getConfiguredKey(checkValid(method));
        this.parser = parser;
        if (parser.get(key) == null) {
            throw new EnvException("Specified key " + key + " is not present ");
        }
        this.initialValue = Utils.convert(parser.get(key), returnType);
    }

    private String checkValid(Method method) {
        if (method.isAnnotationPresent(Bind.class) && method.isAnnotationPresent(Property.class)) {
            throw new EnvException("Method : " + method.getName() + "() cannot have both Bind and Property Annotations");
        }
        if (!Utils.validReturnType(method.getReturnType())) {
            throw new EnvException("Method can have only int,double,float,boolean,Integer,Float,Double,Boolean and String as return types");
        }

        String propertyName = method.getName();
        if (method.isAnnotationPresent(Property.class)) {
            propertyName = method.getAnnotation(Property.class).value();
        } else if (method.isAnnotationPresent(Bind.class)) {
            propertyName = method.getAnnotation(Bind.class).value();
        }
        if (propertyName.isEmpty()) {
            throw new EnvException("Property name cannot be empty");
        }
        return propertyName;
    }

    public Object getValue() {
        if (!isBound)
            return initialValue;
        return Utils.convert(parser.get(key), returnType);
    }


}
