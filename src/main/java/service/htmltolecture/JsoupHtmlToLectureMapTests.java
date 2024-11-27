package service.htmltolecture;

import service.sitereader.JsoupSiteReader;

public class JsoupHtmlToLectureMapTests {
    public static final String URL_1 = "https://javarush.com/ua/quests/lectures/ua.questsyntaxpro.level01.lecture01";

    public static void main(String[] args) {
        JsoupSiteReader jsoupSiteReader = new JsoupSiteReader();
        String html = jsoupSiteReader.read(URL_1);

//        System.out.println(new JsoupHtmlToLectureMap(html).map());
        System.out.println(new JsoupHtmlToLectureMap(html).getLinkToNext());


    }
}
