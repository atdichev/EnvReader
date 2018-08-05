package reader.parsers;

import org.junit.Assert;
import org.junit.Test;
import reader.Constants;

import java.io.File;
import java.net.URL;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class XmlParserTest {


    @Test
    public void testGet() {
        URL url = getClass().getResource(Constants.XML_FILE_PATH);
        XmlParser parser = new XmlParser(new File(url.getFile()));
        Assert.assertEquals("Bruce Wayne", parser.get("name"));
        Assert.assertEquals("42", parser.get("age"));
        Assert.assertEquals("4", parser.get("nest.three.inThree.four"));
    }

}