package com.shenjinxiang.exam.jsoup;

import com.shenjinxiang.exam.jsoup.domain.Student;
import com.shenjinxiang.kit.JsonKit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/21 22:44
 */
public class JsoupTest {

    private static final Logger logger = LoggerFactory.getLogger(JsoupTest.class);

    private static final String url = "http://localhost:8080/students";

    public static List<Student> get() throws IOException {
        Connection connection = Jsoup.connect(url);
        Connection.Response  response = connection.ignoreContentType(true)
//                .ignoreHttpErrors(true)
                .method(Connection.Method.GET)
                .execute();
        List<Student> students = JsonKit.fromJsonAsList(response.body(), Student.class);
        for(Student student: students) {
            logger.info(JsonKit.toJson(student));
        }
        return students;
    }

    public static void delete(int id) throws Exception {
        Connection connection = Jsoup.connect(url + "/" + id);
        Connection.Response  response = connection.ignoreContentType(true)
//                .ignoreHttpErrors(true)
                .method(Connection.Method.DELETE)
                .execute();
        String content = response.body();
        logger.info(content);
    }

    public static void add (Student student) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("name", student.getName());
        data.put("age", "" + student.getAge());
        data.put("sex", "" + student.getSex());
        data.put("desc", student.getDesc());

        Connection connection = Jsoup.connect(url);
        Connection.Response  response = connection.ignoreContentType(true)
                .ignoreHttpErrors(true)
                .method(Connection.Method.POST)
                .header("Content-Type", "application/json")
//                .data(data)
                .requestBody(JsonKit.toJson(student))
                .execute();
        String content = response.body();
        logger.info(content);
    }
}
