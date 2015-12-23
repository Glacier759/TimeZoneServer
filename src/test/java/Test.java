

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Glacierlx on 2015/12/17.
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test.class);
        Map<String,String> parmas = new HashMap<String, String>();
        parmas.put("a", "bbb");
        parmas.put("c", "ddd");
        System.out.println(parmas);
        logger.info("[Test] params: " + parmas);
    }


}




