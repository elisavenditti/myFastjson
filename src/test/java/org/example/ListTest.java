package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class ListTest extends TestCase {

    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{{new LinkedList(), 23L,45L,"[23L,45L]"}});
        // Metodo statico crea l'input fornito all'oggetto testato e descrive l'output atteso
        // Ritorna un insieme (testedInstance, firstParam, secondParam, expectedResult)
    }
    private final List list;                // tested object
    private final long first;               // first parameter
    private final long second;              // second parameter
    private final String expectedResult;    // expected result

    public ListTest(List list, long first, long second, String expectedResult){
        this.list = list;
        this.first = first;
        this.second = second;
        this.expectedResult = expectedResult;
    }

    @Test
    public void test_null() throws Exception {
        list.add(first);
        list.add(second);
        Assert.assertEquals(this.expectedResult, JSON.toJSONString(list, SerializerFeature.WriteClassName));
    }

}
