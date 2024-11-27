package service.saveLecture;

import data.Lecture;
import service.htmltolecture.JsoupHtmlToLectureMap;
import service.sitereader.JsoupSiteReader;

public class FileSaveLectureTests {
    public static final String URL_1 = "https://javarush.com/ua/quests/lectures/ua.questsyntaxpro.level01.lecture01";

    public static void main(String[] args) {
        FileSaveLecture fileSaveLecture = new FileSaveLecture();

        JsoupSiteReader jsoupSiteReader = new JsoupSiteReader();
        String html = jsoupSiteReader.read(URL_1);

        Lecture lecture = new JsoupHtmlToLectureMap(html).map();

        fileSaveLecture.save(lecture, "testName.html");

    }
}
