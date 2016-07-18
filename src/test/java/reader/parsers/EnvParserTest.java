package reader.parsers;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by AshwinR on 7/18/2016.
 */
public class EnvParserTest {

    @Test
    public void testGet() throws Exception {
        final String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.startsWith("win");
        EnvParser parser = new EnvParser();
        //TODO check for other OS
        if (isWindows) {
            Assert.assertNotNull(parser.get("Path"));
        }
    }
}