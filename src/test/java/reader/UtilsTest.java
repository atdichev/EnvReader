package reader;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    @SuppressWarnings("all")
    public void testConversions() {
        Assert.assertEquals(Utils.convert("12", double.class), 12D);
        Assert.assertEquals(Utils.convert("12", int.class), 12);
        Assert.assertEquals(Utils.convert("12.23", int.class), 12);
        Assert.assertEquals(Utils.convert("12.23", Integer.class), 12);
        Assert.assertEquals(Utils.convert("12.23", String.class), "12.23");
    }
}