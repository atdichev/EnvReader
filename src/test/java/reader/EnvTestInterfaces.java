package reader;

import annotation.Bind;
import annotation.Env;
import annotation.Property;

import java.util.List;

@Env
interface EnvTestInterface {

    @Bind("Path")
    String val();

    String Path();
}

@Env(Prefix = "Pa")
interface WithPrefix {
    String th();
}

interface Dummy {

}

interface WrongPropertyName {
    String paths();
}

@Env
interface WithBothPropNdBind {
    @Bind("we")
    @Property("ew")
    String blah();
}

@Env
interface WrongReturnType {
    List<String> Path();
}

@Env
interface WrongPropertyType {
    int Path();
}

