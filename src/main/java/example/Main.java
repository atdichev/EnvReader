package example;

import annotation.Bind;
import annotation.Env;
import annotation.Property;
import reader.EnvReader;
import reader.PropertyParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Env
interface Demo {
    String PAYMENT_ENABLED = "paymentEnabled";
    String DEMO_KEY = "demokey";

    @Bind(DEMO_KEY)
    String val();

    @Property(PAYMENT_ENABLED)
    boolean isPaymentEnabled();
}


public class Main {


    public static void main(String... args) throws IOException {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(Demo.DEMO_KEY, "demovalue");
        map.put(Demo.PAYMENT_ENABLED, "true");
        PropertyParser reader = new PropertyParser() {
            @Override
            public Object get(String property) {
                return map.get(property);
            }
        };
        Demo demo = EnvReader.createReader(Demo.class, reader);
        System.out.println(demo.val());
        System.out.println(demo.isPaymentEnabled());
        map.put(Demo.DEMO_KEY, "changeddemovalue");
        System.out.println(demo.val());

    }
}
