package Controller;

import data.Lecture;
import service.htmltolecture.JsoupHtmlToLectureMap;
import service.saveLecture.FileSaveLecture;
import service.sitereader.JsoupSiteReader;
import service.sitereader.SiteReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaRushParserApp {
    public static final String JAVA_RUSH_LECTURES = "https://javarush.com/ua/quests/lectures/";
    public static final String JAVA_RUSH_SYNTAX_PRO = "ua.questsyntaxpro.level%s.lecture%s";
    public static final String FILE_EXTENTION = ".html";

    public static void main(String[] args) {
        SiteReader siteReader = new JsoupSiteReader();
        FileSaveLecture saveLecture = new FileSaveLecture();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 30; j++) {

                String paramI = String.valueOf(i);

                if (paramI.length() == 1) {
                    paramI = "0" + paramI;
                }

                String paramJ = String.valueOf(j);
                if (paramJ.length() == 1) {
                    paramJ = "0" + paramJ;
                }

                String levName = paramI + " lec-" + paramJ;

                String url
                        = JAVA_RUSH_LECTURES + String.format(JAVA_RUSH_SYNTAX_PRO, paramI, paramJ);

                executorService.submit(() -> {
                    String html = siteReader.read(url);
                    Lecture lecture = new JsoupHtmlToLectureMap(html).map();

                    String fileName = levName
                            + " "
                            + lecture.course()
                            + " "
                            + lecture.topic()
                            + FILE_EXTENTION;

                    if (!lecture.topic().equals("EmptyLecture")) {
                        saveLecture.save(lecture,
                                fileName
                                        .replace(" ", "_")
                                        .replace("?", "--")
                                        .replace(":", "--"));
                        saveInfo(url, fileName);
                    }
                });
            }
        }
        executorService.close();
    }

    private static synchronized void saveInfo(String url, String fileName) {
        try (FileWriter fileWriter
                     = new FileWriter("src/main/java/data/files/files.txt", true)) {
            fileWriter.write(fileName
                    + " ---> "
                    + url
                    + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
