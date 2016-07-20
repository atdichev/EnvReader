package reader;

import annotation.Bind;
import annotation.Property;
import reader.parsers.PropertyParser;

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
        String value = parser.get(key);
        if (value == null) {
            throw new EnvException("Specified key " + key + " is not present ");
        }
        this.initialValue = Utils.convert(value, returnType);
    }

    private String checkValid(Method method) {
        if (method.isAnnotationPresent(Bind.class) && method.isAnnotationPresent(Property.class)) {
            throw new EnvException("Method : " + method.getName() + "() cannot have both Bind and Property Annotations");
        }
        if (!Utils.validReturnType(method.getReturnType())) {
            throw new EnvException("Method can have only int,double,float,boolean,Integer,Float,Double,Boolean and String as return types");
        }

        String propertyName = "";
        if (method.isAnnotationPresent(Property.class)) {
            propertyName = method.getAnnotation(Property.class).value();
        } else if (method.isAnnotationPresent(Bind.class)) {
            propertyName = method.getAnnotation(Bind.class).value();
        }
        if (propertyName.isEmpty()) {
            propertyName = method.getName();
        }
        return propertyName;
    }

    public Object getValue() {
        if (!isBound) {
            return initialValue;
        }
        //get the new value
        parser.reLoad();
        return Utils.convert(parser.get(key), returnType);
    }

}
