import classes.FileReaderUtil;
import classes.Student;
import classes.University;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import classes.ComparatorFactory;
import enumClasses.StudentEnum;
import enumClasses.UniversityEnum;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args)throws IOException {

        FileReaderUtil fileReader = FileReaderUtil.getInstance();

        //Вывод информации о студентах в порядке возрастания оценок
        StudentComparator studentComparator = ComparatorFactory.getStudentComparator(StudentEnum.AVG_EXAM_SCORE);
        List<Student> studentsList = fileReader.readStudentsFromFile("universityInfo.xlsx");

        studentsList.stream().sorted(studentComparator).forEach(System.out::println);

        //Вывод информации об университетах в алфавитном порядке по сокращенному названию
        UniversityComparator universityComparator = ComparatorFactory.getUniversityComparator(UniversityEnum.SHORT_NAME);
        List<University> universitiesList = fileReader.readUniversitiesFromFile("universityInfo.xlsx");

        universitiesList.stream().sorted(universityComparator).forEach(System.out::println);
    }
}