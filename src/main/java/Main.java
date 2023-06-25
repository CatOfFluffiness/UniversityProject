import Utils.FileReaderUtil;
import Utils.JsonUtil;
import classes.*;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enumClasses.StudentEnum;
import enumClasses.UniversityEnum;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        System.out.println("JSON-строки студентов: " + studentJson + "\n");
        // Де сериализация полученной JSON-строки студентов обратно в список студентов
        List<Student> deserializedStudentsList = JsonUtil.deserializeStudentList(studentJson);


        //Вывод информации об университетах в алфавитном порядке по сокращенному названию
        UniversityComparator universityComparator = ComparatorFactory.getUniversityComparator(UniversityEnum.SHORT_NAME);
        List<University> universitiesList = fileReader.readUniversitiesFromFile("universityInfo.xlsx");

        universitiesList.stream().sorted(universityComparator).forEach(System.out::println);

        // Сериализация списка университетов
        String universityJson = JsonUtil.serializeUniversityList(universitiesList);
        // Вывод JSON-строк в консоль
        System.out.println("JSON-строки университетов: " + universityJson + "\n");
        // Де сериализация полученной JSON-строки университетов обратно в список студентов
        List<University> deserializedUniversityList = JsonUtil.deserializeUniversityList(universityJson);

        // Проверка сериализации и десериализации отдельных объектов студентов и вывод результатов сравнения отдельных объектов
        AtomicBoolean allStudentObjectsIdentical = new AtomicBoolean(true);
        studentsList.forEach(student -> {
            String studentObjectJson = JsonUtil.serializeStudent(student);
            // Проверка json из отдельного элемента
            Student studentFromJson = JsonUtil.deserializeStudent(studentObjectJson);
            // Проверка воссоздания
            boolean studentIsIdentical = student.equals(studentFromJson);
            System.out.println("Воссозданный и оригинальный объект " + student.getFullName() + " идентичны: " + studentIsIdentical);
            if (! studentIsIdentical) {
                // Если найден хотя бы один неидентичный объект, устанавливаем флаг на false
                allStudentObjectsIdentical.set(false);
            }
        });

        // Проверка сериализации и десериализации отдельных объектов университетов и вывод результатов сравнения отдельных объектов
        AtomicBoolean allUniversityObjectsIdentical = new AtomicBoolean(true);
        universitiesList.forEach(university -> {
            String universityObjectJson = JsonUtil.serializeUniversity(university);
            // Проверка json из отдельного элемента
            University universityFromJson = JsonUtil.deserializeUniversity(universityObjectJson);
            // Проверка воссоздания
            boolean universityIsIdentical = university.equals(universityFromJson);
            System.out.println("Воссозданный и оригинальный объект " + university.getFullName() + " идентичны: " + universityIsIdentical);
            if (! universityIsIdentical) {
                // Если найден хотя бы один неидентичный объект, устанавливаем флаг на false
                allUniversityObjectsIdentical.set(false);
            }
        });

        //Вывод общих результатов проверки по всем университетам и студентам, что бы не изучать отдельные элементы в консоли
        if (allUniversityObjectsIdentical.get()) {
            System.out.println("Все объекты университетов идентичны!");
        } else {
            System.out.println("Не все объекты университетов идентичны!");
        }
        if (allStudentObjectsIdentical.get()) {
            System.out.println("Все объекты студентов идентичны!");
        } else {
            System.out.println("Не все объекты студентов идентичны!");
        }


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