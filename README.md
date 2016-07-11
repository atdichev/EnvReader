# EnvReader
A Java configuration management solution based on annotations.
By default properties are read from system environment variables but you can easily provide your own implementations to read from other locations 
and formats like JSON,TOML, YAML etc.

======
###Example

```java

@Env //required
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
Config config = EnvReader.createReader(Config.class); //reads from system env

```
======
###Custom Parsers
Custom Property parsers can be provided by implementing PropertyParser interface.
####Example
```java
//PropertyParser to read from .properties file
public class PropertyFilesParser implements PropertyParser {

    private final Properties properties;

    public PropertyFilesParser(String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        properties = new Properties();
        properties.load(inputStream);
    }

    @Override
    public Object get(String property) {
        return properties.getProperty(property); // no need to handle type conversions here
    }
}
//creation 
Config config = EnvReader.createReader(Config.class , new PropertyFilesParser("app.properties") );//now reads from app.properties file 
```
Similarly parsers can be implemented and used for other formats.

======
###Note
With @Bind annotation, the PropertyParser's get method is called each time when the property is accessed and that value is returned.
Otherwise the initital value of the property which is read when creating the reader is returned.
