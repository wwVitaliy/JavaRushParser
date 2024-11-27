package service.sitereader;

public class JsoupSiteReaderTests {
    public static final String URL_1 = "https://javarush.com/ua/quests/lectures/ua.questsyntaxpro.level01.lecture00";
    public static final String URL_2 = "https://javarush.com/ua/quests/lectures/ua.questsyntaxpro.level03.lecture03";
    public static final String URL_3 = "https://javarush.com/ua/quests/lectures/ua.questsyntaxpro.level.lecture";

    public static void main(String[] args) {
        JsoupSiteReader jsoupSiteReader = new JsoupSiteReader();

//        System.out.println(jsoupSiteReader.read(URL_1));
//        System.out.println(jsoupSiteReader.read(URL_2));
        System.out.println(jsoupSiteReader.read(URL_3));

    }
}
