package reader.parsers;

import org.junit.Assert;
import org.junit.Test;
import reader.Constants;

import java.io.File;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class YamlParserTest {

    @Test
    public void testGet() {
        YamlParser parser = new YamlParser(new File(Constants.YAML_FILE_PATH));
        Assert.assertEquals("Bruce Wayne", parser.get("name"));
        Assert.assertEquals("42", parser.get("age"));
        Assert.assertEquals("4", parser.get("nest.three.inThree.four"));
    }

}