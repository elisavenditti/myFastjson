package org.example;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IdentityHashMap;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserConfigTest2 extends TestCase {
    @Parameters
    public static Collection<Object[]> data() {
        ObjectDeserializer deserializer = new ObjectDeserializer() {
            @Override
            public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
                return null;
            }

            @Override
            public int getFastMatchToken() {
                return 0;
            }
        };
        ParserConfig p = new ParserConfig();
        return Arrays.asList(new Object[][]{
                {p, deserializer}
        });
    }

    private ParserConfig parserConfig;
    private ObjectDeserializer deserializer;
    private Type type;

    private void configure(ObjectDeserializer deserializer) {
        this.deserializer = deserializer;
        this.type = new Type() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
        this.parserConfig.putDeserializer(this.type, deserializer);
    }


    public ParserConfigTest2(ParserConfig parserConfig, ObjectDeserializer deserializer) {
        this.parserConfig = parserConfig;
        configure(deserializer);
    }


    @Test
    public void testGetDeserializer() {
        IdentityHashMap<Type, ObjectDeserializer> deserializers = this.parserConfig.getDeserializers();
        ObjectDeserializer readDeserializer = deserializers.get(this.type);
        assertEquals(this.deserializer, readDeserializer);
    }

}