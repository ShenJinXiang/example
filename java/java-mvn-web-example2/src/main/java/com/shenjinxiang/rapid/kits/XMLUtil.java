package com.shenjinxiang.rapid.kits;

import com.jfinal.kit.JsonKit;
import org.apache.ibatis.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjinxiang on 2017-11-29.
 */
public class XMLUtil {

    public static Map<String, Object> text2Map(String text) {
        Map<String, Object> map = new HashMap<>();
        try{
            Document document = DocumentHelper.parseText(text);
            Element root = document.getRootElement();
            map = (Map<String, Object>) element2Map(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Object element2Map(Element element) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Element> elements = element.elements();
        if (elements.size() > 0) {
            for (Element ele : elements) {
                if (map.containsKey(ele.getName())) {
                    List list = new ArrayList();
                    if (map.get(ele.getName()) instanceof  List) {
                        list.addAll((List)map.get(ele.getName()));
                    } else {
                        list.add(map.get(ele.getName()));
                    }
                    Object obj = element2Map(ele);
                    list.add(obj);
                    map.put(ele.getName(), list);
                } else {
                    map.put(ele.getName(), element2Map(ele));
                }
            }
            return map;
        }
        return element.getTextTrim();
    }

    public static void main(String[] args) throws IOException {
        String src = "config/dbConfig.xml";
        Reader reader = Resources.getResourceAsReader(src);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String text = stringBuilder.toString();
        Map<String, Object> map = XMLUtil.text2Map(text);
        System.out.println(JsonKit.toJson(map));

//        SAXReader reader = new SAXReader();

    }
}
