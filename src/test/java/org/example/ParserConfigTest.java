package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserConfigTest extends TestCase {

    @Parameters
    public static Collection<Object[]> data(){
        ParserConfig p = new ParserConfig();
        String s = "{\"value\":123}";
        return Arrays.asList(new Object[][]{
                {p, s, 123, false},
                {null, s, 123, true},
                {p, null, null, true},
                {p, "", null, false}
        });
    }

    private ParserConfig parserConfig;
    private String first;
    private Integer expectedResult;
    private boolean expectedException;

    private void configure(ParserConfig parserConfig){
        this.parserConfig = parserConfig;
        if(this.parserConfig!=null)
            this.parserConfig.setDefaultClassLoader(Thread.currentThread().getContextClassLoader());
    }


    public ParserConfigTest(ParserConfig parserConfig, String first, Integer expectedResult, boolean exception){
        this.first = first;
        this.expectedResult = expectedResult;
        this.expectedException = exception;
        configure(parserConfig);
    }



    @Test
    public void myTest_1(){

        try {
            Integer result;
            Model model = JSON.parseObject(this.first, Model.class, this.parserConfig);
            System.out.println(1);
            if(model==null) result = null;
            else result = model.value;
            Assert.assertEquals(this.expectedResult, result);
        } catch (NullPointerException e){
            System.out.println(2);
            assertTrue(this.expectedException);
        }

    }


    public static class Model {
        public int value;
    }

}
