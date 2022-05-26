package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserConfigTest extends TestCase {

    @Parameters
    public static Collection<Object[]> data(){
        // Crea l'input fornito all'oggetto testato e descrive l'output atteso
        // Ritorna un insieme (testedInstance, firstParam, expectedResult)

        ParserConfig p = new ParserConfig();
        return Arrays.asList(new Object[][]{{p,"{\"value\":123}", 123}});
    }

    private final ParserConfig parserConfig;    // tested object
    private final String first;                 // provided input
    private final int expectedResult;           // expected result
//    private Model model;

    private void configure(){
        this.parserConfig.setDefaultClassLoader(Thread.currentThread().getContextClassLoader());
//        this.model = JSON.parseObject(this.first, Model.class, this.parserConfig);
    }


    public ParserConfigTest(ParserConfig parserConfig, String first, int expectedResult){
        // Gli argomenti del costruttore sono forniti dal RUNNER che li ottiene invocando il metodo data()
        this.parserConfig = parserConfig;
        this.first = first;
        this.expectedResult = expectedResult;
        configure(); //mettici i parametriiii --> se lo faccio però mi dà errore ...
    }

   /*
    @Test
    public void myTest_0(){
        this.parserConfig.getDeserializers();
    }
    */
    // assumendo che l'istanza testata sia la configurazione
    @Test
    public void myTest_1(){
        // com.alibaba.json.bvt.serializer.ParserConfigTest.Model model = JSON.parseObject("{\"value\":123}", com.alibaba.json.bvt.serializer.ParserConfigTest.Model.class, this.parserConfig);
        Model model = JSON.parseObject(this.first, Model.class, this.parserConfig);
        Assert.assertEquals(this.expectedResult, model.value);
    }


//    // assumendo che l'istanza testata sia il Parser JSON --> model diventa un input che devo passare con il parametrized
//    @Test
//    public void myTest_1_bis() throws Exception{
//        Assert.assertEquals(this.expectedResult, model.value);
//    }

    public static class Model {
        public int value;
    }
}
