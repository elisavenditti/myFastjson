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
public class ListTest2 extends TestCase{

    @Parameters
    public static Collection<Object[]> data(){
        List<String> f = new ArrayList<>();
        f.add("Apple");
        f.add("Kiwi");
        f.add("Melon");
        f.add("Orange");
        Fruit fruit = new Fruit(f);
        return Arrays.asList(new Object[][]{
                {"prova", SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName, "\"prova\""},
                {10, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty, "10"},
                {fruit, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName, "{\n" +
                        "\t\"@type\":\"org.example.ListTest2$Fruit\",\n" +
                        "\t\"available\":[\n" +
                        "\t\t\"Apple\",\n" +
                        "\t\t\"Kiwi\",\n" +
                        "\t\t\"Melon\",\n" +
                        "\t\t\"Orange\"\n" +
                        "\t]\n" +
                        "}"},
                {(int)10., SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty, "10"}
        });

    }
    private Object object;
    private SerializerFeature feature1;
    private SerializerFeature feature2;
    private String expectedResult;



    public ListTest2(Object object, SerializerFeature feature1, SerializerFeature feature2, String expectedResult){

        this.object =object;
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testConversionListToJsonString() {

        String actual;
        try {
            actual = JSON.toJSONString(this.object, this.feature1, this.feature2);
        } catch (NullPointerException e){
            actual = "exception";
        }
        Assert.assertEquals(this.expectedResult, actual);
    }

    public static class Fruit{
        public List<String> available;
        public Fruit(List<String> fruitAvailable){
            available = new ArrayList<>();
            available.addAll(fruitAvailable);
        }
    }

}
