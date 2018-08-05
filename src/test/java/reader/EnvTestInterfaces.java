package reader;

import annotation.Bind;
import annotation.Env;
import annotation.Property;

import java.util.List;

@Env(type = Type.JSON, file = Constants.JSON_FILE_PATH)
interface EnvTestInterface {

    String name();

    int age();

}

interface Config {

    String name();

    @Bind
    int age();

    String city();

    @Property("nest.one")
    int one();

    @Property("nest.two")
    int two();

    @Property("nest.three.inThree.four")
    int four();

    boolean greatestSuperHero();

}


@Env(type = Type.JSON, file = Constants.JSON_FILE_PATH)
interface JsonConfig extends Config {

}

@Env(type = Type.XML, file = Constants.XML_FILE_PATH)
interface XmlConfig extends Config {
}

@Env(type = Type.YAML, file = Constants.YAML_FILE_PATH)
interface YamlConfig extends Config {
}

@Env(type = Type.PROPERTIES, file = Constants.PROPERTIES_FILE_PATH)
interface PropertiesConfig {
    String name();

    int age();

    String city();
}

@Env(type = Type.JSON, file = "/blahblahblah")
interface InvalidFileLocationInterface {

}

@Env(Prefix = "na", type = Type.JSON, file = Constants.JSON_FILE_PATH)
interface WithPrefix {
    String me();
}

interface Dummy {

}

@Env
interface WrongPropertyName {
    String blahblah();
}

@Env
interface WithBothPropNdBind {
    @Bind("name")
    @Property("name")
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

@Env(type = Type.JSON, file = Constants.JSON_FILE_PATH)
interface AnnotationWithDefaultValue {
    @Bind
    String name();
}

@Env(type = Type.TEXT)
interface AnnotationText {
    @Bind("/file1.txt")
    String content1();
    
    @Bind("/file2.txt")
    String content2();
    
    @Bind("/file3")
    String file3();    
}
