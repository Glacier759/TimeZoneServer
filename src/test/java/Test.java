

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Student;
import org.apache.log4j.Logger;


/**
 * Created by Glacierlx on 2015/12/17.
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test.class);

        String json = "{\"status\":200,\"info\":{\"stuId\":\"04121110\",\"stuClass\":\"计科1204\",\"id\":1,\"stuName\":\"任立翔\",\"accessToken\":\"7f145147b41f03b24fc8cb9a0a1a34bd\",\"stuMajor\":\"计算机科学与技术\",\"stuIntroduction\":\"这是全新的自我介绍哟~\"}}";
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject stuInfo = jsonObject.getJSONObject("info");
        Student student = new Student();
        student.setId(stuInfo.getInteger("id"));
        student.setStuId(stuInfo.getString("stuId"));
        student.setStuName(stuInfo.getString("stuName"));
        student.setStuClass(stuInfo.getString("stuClass"));
        student.setStuMajor(stuInfo.getString("stuMajor"));
        student.setAccessToken(stuInfo.getString("accessToken"));
        student.setStuIntroduction(stuInfo.getString("stuIntroduction"));
        System.out.println(student);

    }


}




