package reader.parsers;

import org.junit.Assert;
import org.junit.Test;
import reader.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class JavaPropertiesParserTest {

    @Test
    public void testGet() throws FileNotFoundException {
        URL url = getClass().getResource(Constants.PROPERTIES_FILE_PATH);
        JavaPropertiesParser parser = new JavaPropertiesParser(new FileInputStream(new File(url.getFile())));
        Assert.assertEquals("Bruce Wayne", parser.get("name"));
        Assert.assertEquals("42", parser.get("age"));
        Assert.assertEquals("Gotham", parser.get("city"));
    }

}