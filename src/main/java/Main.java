import classes.FileReaderUtil;
import classes.Student;
import classes.University;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args)throws IOException {

            FileReaderUtil fileReader = FileReaderUtil.getInstance();

            List<Student> studentsList = fileReader.readStudentsFromFile("universityInfo.xlsx");
            for (Student student : studentsList) {
                System.out.println(student);
            }

            List<University> universitiesList = fileReader.readUniversitiesFromFile("universityInfo.xlsx");
            for (University university : universitiesList) {
                System.out.println(university);
            }
    }
}