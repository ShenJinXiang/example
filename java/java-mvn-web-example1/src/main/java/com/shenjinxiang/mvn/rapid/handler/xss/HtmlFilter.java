package com.shenjinxiang.mvn.rapid.handler.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HtmlFilter {

    public static String getBasicHtmlandimage(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.relaxed());
    }


    public static void main(String[] args) {
        String str = "<script src='srcluj'>脚本</script>sdfsf <a>aaa</a>松岛枫松岛枫<img src='imgsrc' />aaaaaaaaaa";
        System.out.println(Jsoup.clean(str, Whitelist.none()));
        System.out.println("-----------------------");
        System.out.println(Jsoup.clean(str, Whitelist.basic()));
        System.out.println("-----------------------");
        System.out.println(Jsoup.clean(str, Whitelist.basicWithImages()));
        System.out.println("-----------------------");
        System.out.println(Jsoup.clean(str, Whitelist.relaxed()));
        System.out.println("-----------------------");
        System.out.println(Jsoup.clean(str, Whitelist.simpleText()));
    }
}
