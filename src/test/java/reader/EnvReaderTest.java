package reader;

import org.junit.Assert;
import org.junit.Test;

import com.azsoftware.envreader.reader.EnvException;
import com.azsoftware.envreader.reader.EnvReader;

public class EnvReaderTest {

    @Test
    public void testNotReturnNull() {
        Assert.assertNotNull(EnvReader.createReader(EnvTestInterface.class));
    }

    @Test(expected = EnvException.class)
    public void testNotAcceptOtherThanInterface() {
        Assert.assertNotNull(EnvReader.createReader(EnvReaderTest.class));
    }

    @Test(expected = NullPointerException.class)
    public void testThrowNPEOnNullClassObjectParameter() {
        Assert.assertNotNull(EnvReader.createReader(null));
    }

    @Test()
    public void testAcceptsOnlyInterface() {
        Assert.assertNotNull(EnvReader.createReader(EnvTestInterface.class));
    }

    @Test(expected = EnvException.class)
    public void testInterfaceHasENVAnnotation() {
        EnvReader.createReader(Dummy.class);
    }

    @Test(expected = EnvException.class)
    public void testValidReturnTypes() {
        EnvReader.createReader(WrongReturnType.class);
    }

    @Test(expected = EnvException.class)
    public void testThrowsExceptionOnNonExistingFile() {
        EnvReader.createReader(InvalidFileLocationInterface.class);
    }

    @Test(expected = EnvException.class)
    public void testInterfaceHasUnknownProperty() {
        EnvReader.createReader(WrongPropertyName.class);
    }

    @Test(expected = EnvException.class)
    public void testInterfaceMethodHasFieldWithBothBindAndPropertyAnnotations() {
        EnvReader.createReader(WithBothPropNdBind.class);
    }

    @Test(expected = Exception.class)
    public void testWrongPropertyTypes() {
        EnvReader.createReader(WrongPropertyType.class);
    }

    @Test(expected = SecurityException.class)
    public void TestWithSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        securityManager.checkPropertiesAccess();
        System.setSecurityManager(securityManager);
        EnvReader.createReader(EnvTestInterface.class);
    }

    @Test
    public void TestWithPrefix() {
        WithPrefix withPrefix = EnvReader.createReader(WithPrefix.class);
        Assert.assertNotNull(withPrefix.me());
    }

    private void testValues(Config config) {
        Assert.assertEquals("Bruce Wayne", config.name());
        Assert.assertEquals("Gotham", config.city());
        Assert.assertEquals(42, config.age());
        Assert.assertEquals(1, config.one());
        Assert.assertEquals(2, config.two());
        Assert.assertEquals(4, config.four());
        Assert.assertEquals(true, config.greatestSuperHero());
    }

    @Test
    public void TestJsonConfig() {
        testValues(EnvReader.createReader(JsonConfig.class));
    }

    @Test
    public void TestXmlConfig() {
        testValues(EnvReader.createReader(XmlConfig.class));
    }

    @Test
    public void TestYamlConfig() {
        testValues(EnvReader.createReader(YamlConfig.class));
    }


    @Test
    public void TestPropertiesConfig() {
        PropertiesConfig config = EnvReader.createReader(PropertiesConfig.class);
        Assert.assertEquals("Bruce Wayne", config.name());
        Assert.assertEquals("Gotham", config.city());
        Assert.assertEquals(42, config.age());
    }

    @Test
    public void testTakeMethodNameAsKeyWhenBindValueEmpty() {
        Assert.assertNotNull(EnvReader.createReader(AnnotationWithDefaultValue.class));
    }
    
    @Test
    public void testTakeText() {
        Assert.assertNotNull(EnvReader.createReader(AnnotationText.class));
    }

}
