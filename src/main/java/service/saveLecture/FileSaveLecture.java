package service.saveLecture;

import data.Lecture;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaveLecture implements SaveLecture {

    public static final String FILES_PATH = "src/main/java/data/files/";

    @Override
    public void save(Lecture lecture, String name) {

        try (FileWriter fileWriter = new FileWriter(new File(FILES_PATH + name))) {
            fileWriter.write(lecture.text());
        } catch (IOException e) {
            System.out.println("File cannot be written: " + FILES_PATH + name);
        }
    }
}
