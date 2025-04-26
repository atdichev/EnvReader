package reader;

import org.junit.Assert;
import org.junit.Test;

import com.azsoftware.envreader.reader.Utils;

public class UtilsTest {
    @Test
    @SuppressWarnings("all")
    public void testConversions() {
        Assert.assertEquals(12D, Utils.convert("12", double.class));
        Assert.assertEquals(12, Utils.convert("12", int.class));
        Assert.assertEquals(12, Utils.convert("12.23", int.class));
        Assert.assertEquals(12, Utils.convert("12.23", Integer.class));
        Assert.assertEquals("12.23", Utils.convert("12.23", String.class));
        Assert.assertEquals(true, Utils.convert("true", boolean.class));
    }
}