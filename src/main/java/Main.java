import Utils.*;
import classes.*;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enumClasses.StudentEnum;
import enumClasses.UniversityEnum;
import filehandling.FileReaderUtil;
import filehandling.XlsWriter;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import static java.util.logging.Level.INFO;


public class Main {
    private static final  Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)throws IOException {

        //Начало работы логгера в классе мейн
        LogManager.getLogManager().readConfiguration(
               Main.class.getResourceAsStream("/logging.properties"));
        logger.log(INFO, "Старт работы программы");

        FileReaderUtil fileReader = FileReaderUtil.getInstance();
        //Вывод информации о студентах в порядке возрастания оценок
        StudentComparator studentComparator = ComparatorFactory.getStudentComparator(StudentEnum.AVG_EXAM_SCORE);
        List<Student> studentsList = fileReader.readStudentsFromFile("src/main/resources/universityInfo.xlsx");

        studentsList.stream().sorted(studentComparator).forEach(System.out::println);

        // Сериализация списка студентов
        String studentJson = JsonUtil.serializeStudentList(studentsList);
        // Вывод JSON-строк в консоль
        System.out.println("JSON-строки студентов: " + studentJson + "\n");
        // Де сериализация полученной JSON-строки студентов обратно в список студентов
        List<Student> deserializedStudentsList = JsonUtil.deserializeStudentList(studentJson);

        //Вывод информации об университетах в алфавитном порядке по сокращенному названию
        UniversityComparator universityComparator = ComparatorFactory.getUniversityComparator(UniversityEnum.SHORT_NAME);
        List<University> universitiesList = fileReader.readUniversitiesFromFile("src/main/resources/universityInfo.xlsx");

        universitiesList.stream().sorted(universityComparator).forEach(System.out::println);

        // Сериализация списка университетов
        String universityJson = JsonUtil.serializeUniversityList(universitiesList);
        // Вывод JSON-строк в консоль
        System.out.println("JSON-строки университетов: " + universityJson + "\n");
        // Де сериализация полученной JSON-строки университетов обратно в список студентов
        List<University> deserializedUniversityList = JsonUtil.deserializeUniversityList(universityJson);

        // Проверка сериализации и десериализации отдельных объектов студентов и университетов, вывод результатов сравнения отдельных объектов
        ObjectIdentityChecker.checkAllObjectsIdentity(studentsList, universitiesList);

        //Сравнение количества элементов в исходной и в десериализованной коллекциях
        ListSizeChecker.isListsSizeEqual(studentsList, deserializedStudentsList, "студентов");
        ListSizeChecker.isListsSizeEqual(universitiesList, deserializedUniversityList, "университетов");

        //Вызов методов создания статистики и записи её в файл
        List<Statistics> statisticsList = CollectionStatisticsUtil.createStatistics(studentsList, universitiesList);
        XlsWriter.generateTableAndWriteToFile(statisticsList, "statistics.xlsx");

        logger.log(INFO, "Приложение завершило работу.");
    }
}