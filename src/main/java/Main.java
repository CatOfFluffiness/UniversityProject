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

        //Сортировка студентов
        StudentComparator studentComparator = ComparatorFactory.getStudentComparator(StudentEnum.AVG_EXAM_SCORE);
        List<Student> studentsList = fileReader.readStudentsFromFile("src/main/resources/universityInfo.xlsx");
        studentsList.sort(studentComparator);

        // Сериализация списка студентов
        String studentJson = JsonUtil.serializeStudentList(studentsList);
        // Де сериализация полученной JSON-строки студентов обратно в список студентов
        List<Student> deserializedStudentsList = JsonUtil.deserializeStudentList(studentJson);

        //Сортировка университетов
        UniversityComparator universityComparator = ComparatorFactory.getUniversityComparator(UniversityEnum.SHORT_NAME);
        List<University> universitiesList = fileReader.readUniversitiesFromFile("src/main/resources/universityInfo.xlsx");
        universitiesList.sort(universityComparator);

        // Сериализация списка университетов
        String universityJson = JsonUtil.serializeUniversityList(universitiesList);
        // Десериализация полученной JSON-строки университетов обратно в список студентов
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