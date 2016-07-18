package annotation;

import reader.Type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Env {

    String Prefix() default "";

    Type type() default Type.SYSTEM_ENV;

    String file() default "";

}
