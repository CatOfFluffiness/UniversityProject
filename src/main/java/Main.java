import classes.*;
import comparators.StudentComparator;
import comparators.UniversityComparator;
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

        // Сериализация списка студентов
        String studentJson = JsonUtil.serializeStudentList(studentsList);
        // Вывод JSON-строк в консоль
        System.out.println(studentJson);
        // Де сериализация полученной JSON-строки студентов обратно в список студентов
        List<Student> deserializedStudentsList = JsonUtil.deserializeStudentList(studentJson);


        //Вывод информации об университетах в алфавитном порядке по сокращенному названию
        UniversityComparator universityComparator = ComparatorFactory.getUniversityComparator(UniversityEnum.SHORT_NAME);
        List<University> universitiesList = fileReader.readUniversitiesFromFile("universityInfo.xlsx");

        universitiesList.stream().sorted(universityComparator).forEach(System.out::println);

        // Сериализация списка университетов
        String universityJson = JsonUtil.serializeUniversityList(universitiesList);
        // Вывод JSON-строк в консоль
        System.out.println(universityJson);
        // Де сериализация полученной JSON-строки университетов обратно в список студентов
        List<University> deserializedUniversityList = JsonUtil.deserializeUniversityList(universityJson);

        //Сравнение количества элементов в исходной и в десериализованной коллекциях студентов
        System.out.println((studentsList.size() == deserializedStudentsList.size()) ? ("Размеры исходного и десериализованного листов студентов одинаковые!" +
                " Исходного: " + studentsList.size() + " Десериализованного " +deserializedStudentsList.size())
                : ("Размеры исходного и десериализованного листов отличаются!" + " Исходного: " + studentsList.size() + " Десериализованного " +deserializedStudentsList.size()));

        //Сравнение количества элементов в исходной и в десериализованной коллекциях университетов
        System.out.println((universitiesList.size() == deserializedUniversityList.size()) ? ("Размеры исходного и десериализованного листов университетов одинаковые!" +
                " Исходного: " + universitiesList.size() + " Десериализованного " +deserializedUniversityList.size())
                : ("Размеры исходного и десериализованного листов университетов отличаются!" + universitiesList.size() + " Десериализованного " +deserializedUniversityList.size()));
    }
}