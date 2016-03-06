package reader;

import org.junit.Assert;
import org.junit.Test;

public class EnvReaderTest {
    @Test
    public void testNotReturnNull() {
        Assert.assertNotNull("EnvReader should not return null", EnvReader.createReader(EnvTestInterface.class));
    }

    @Test(expected = EnvException.class)
    public void testAcceptNotAcceptOtherThanInterface() {
        Assert.assertNotNull("EnvReader should accept only interface", EnvReader.createReader(EnvReaderTest.class));
    }

    @Test()
    public void testAcceptsOnlyInterface() {
        Assert.assertNotNull("EnvReader should accept only interface", EnvReader.createReader(EnvTestInterface.class));
    }

    @Test(expected = EnvException.class)
    public void testInterfaceHasENVAnnotation() {
        Assert.assertNotNull("Interface should have Env annotation", EnvReader.createReader(Dummy.class));
    }

    @Test(expected = EnvException.class)
    public void testInterfaceHasUnknownProperty() {
        Assert.assertNotNull("Interface should have only existing env variables on system", EnvReader.createReader(WrongPropertyName.class));
    }

    @Test(expected = EnvException.class)
    public void testInterfaceMethodHasFieldWithBothBindAndPropertyAnnotations() {
        Assert.assertNotNull("Interface method should not have both bind and property annotations", EnvReader.createReader(WithBothPropNdBind.class));
    }

    @Test(expected = EnvException.class)
    public void testValidReturnTypes() {
        Assert.assertNotNull("Interface method should have only int,float,double (or) boxed and String", EnvReader.createReader(WrongReturnType.class));
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
        EnvTestInterface envTestInterface = EnvReader.createReader(EnvTestInterface.class);

    }


    @Test
    public void TestWithPrefix() {
        WithPrefix withPrefix = EnvReader.createReader(WithPrefix.class);
        Assert.assertNotNull("Should work with Prefix set", withPrefix.th());

    }


}
