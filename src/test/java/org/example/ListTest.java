package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

@RunWith(Parameterized.class)
public class ListTest extends TestCase {

    @Parameters
    public static Collection<Object[]> data(){

        List l1 = new LinkedList();
        l1.add(23L);
        l1.add(45L);

        List l2 = new LinkedList();
        l2.addAll(l1);
        l2.add(5L);

        String resL1 = "[23L,45L]";
        return Arrays.asList(new Object[][]{
                {l1, SerializerFeature.WriteClassName, resL1},
                {new LinkedList<>(), SerializerFeature.WriteClassName, "[]"},
                {null, SerializerFeature.WriteClassName, "null"},
                {l1, null, "exception"},
                {l1, SerializerFeature.PrettyFormat, "[23,45]"}
        });

    }
    private List list;
    private SerializerFeature feature;
    private String expectedResult;


    private void configure(List list) {
        if(list == null){
            this.list = list;
            return;
        }
        this.list = new LinkedList();
        for (Object elem : list)
            this.list.add(elem);

    }

    public ListTest(List list, SerializerFeature feature, String expectedResult){

        configure(list);
        this.feature = feature;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testConversionListToJsonString() {

        String actual;
        try {
            actual = JSON.toJSONString(this.list, this.feature);
        } catch (NullPointerException e){
            actual = "exception";
        }
        Assert.assertEquals(this.expectedResult, actual);
    }

}
