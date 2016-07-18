# EnvReader 
[![Build Status](https://travis-ci.org/ashrko619/EnvReader.svg?branch=master)](https://travis-ci.org/ashrko619/EnvReader)
Type safe Java configuration management solution completeley based on annotations.
Supports JSON, XML, YAML, Java properties and System environment variables.
Binding is supported to read the latest values from the corresponding source.


### Example

```java
@Env(type = Type.JSON, file = "src/config/config.json" )
interface Config {
  
    //by default method name is the property key
    int port();
  
    //@Property annotation can be used to use custom names for property key
    @Property("dburl")
    String getDbUrl();
  
    //@Bind annotation binds the property key with value. Each time the updated value is read
    @Bind("debugflag")
    boolean isDebug(); // type conversions are handled 
}

//create
Config config = EnvReader.createReader(Config.class);

```
Reading from System environment variables
```java
@Env // by default values are read from system env
interface Config {
    // declarations
}
```

Reading from Xml
```java
@Env(type = Type.XML, file = "src/config/config.xml")
interface Config {
    // declarations
}
```

Reading from Yaml
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
