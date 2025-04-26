package com.azsoftware.envreader.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.azsoftware.envreader.reader.Type;

@Retention(RetentionPolicy.RUNTIME)
public @interface Env {

    String Prefix() default "";

    Type type() default Type.SYSTEM_ENV;

    String file() default "";

}
