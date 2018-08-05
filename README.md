# EnvReader

Type safe Java configuration management solution completeley based on annotations.
Supports JSON, XML, YAML, Java properties, TEXT files and System environment variables.
Binding is supported to read the latest values from the corresponding source.


### Example

```java
@Env // By default values are read from system env
interface Config {

    //by default method name is the property key
    int port();

    //@Property annotation can be used to use custom names for property key
    @Property("dburl")
    String getDbUrl();

    //@Bind annotation binds the property key with value. Each time the updated value is read
    @Bind("debugflag")
    boolean isDebug(); // type conversions are handled

    @Bind
    boolean enableLog();
}

//create
Config config = EnvReader.createReader(Config.class);

```
Reading from JSON
```java
@Env(type = Type.JSON, file = "src/config/config.json" )
interface Config {
    // declarations
}
```

Reading from XML
```java
@Env(type = Type.XML, file = "src/config/config.xml")
interface Config {
    // declarations
}
```

Reading from YAML
```java
@Env(type = Type.YAML, file = "src/config/config.yaml")
interface Config {
    // declarations
}
```

Reading from Java properties file
```java
@Env(type = Type.PROPERTIES, file = "src/config/config.properties")
interface Config {
    // declarations
}
```

Reading from text file
```java
@Env(type = Type.TEXT)
interface AnnotationText {

    @Bind("/file1.txt")
    String content1();

    @Bind("/file2.txt")
    String content2();

    @Bind("/file3")
    String file3();
}
```

EnvReader also supports inherited interfaces.

```java

interface Config {
    int port();
    // other declarations
}
@Env(type = Type.JSON, file = "src/config.json")
interface JsonConfig extends Config {
    // properties are read from "src/config.json"
}

//Usage
Config config = EnvReader.createReader(JsonConfig.class);
```


### Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-awesome-feature`)
3. Commit your changes (`git commit -am 'Add awesome feature'`)
4. Push to the branch (`git push origin my-awesome-feature`)
5. Create new pull request
