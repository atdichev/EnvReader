package reader.parsers;

import org.junit.Assert;
import org.junit.Test;

import com.azsoftware.envreader.reader.parsers.JsonParser;

import reader.Constants;

import java.io.File;
import java.net.URL;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class JsonParserTest {

    @Test
    public void testGet() {
        URL url = getClass().getResource(Constants.JSON_FILE_PATH);
        JsonParser parser = new JsonParser(new File(url.getFile()));
        Assert.assertEquals("Bruce Wayne", parser.get("name"));
        Assert.assertEquals("42", parser.get("age"));
        Assert.assertEquals("4", parser.get("nest.three.inThree.four"));
    }

}