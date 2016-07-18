package reader.parsers;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class EnvParserTest {

    @Test
    public void testGet() throws Exception {
        EnvParser parser = new EnvParser();
        Assert.assertNotNull(parser.get("Path"));
    }
}