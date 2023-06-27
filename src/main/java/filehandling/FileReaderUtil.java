package filehandling;

import classes.Student;
import classes.University;
import enumClasses.StudyProfile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileReaderUtil {

    private static final Logger logger = Logger.getLogger(FileReaderUtil.class.getName());

    //Использую здесь Singleton, чтобы гарантировать единственный экземпляр класса FileReaderUtil в приложении.
    private FileReaderUtil() {}
    private static FileReaderUtil instance = null;
    public static FileReaderUtil getInstance() {
        if (instance == null) {
            instance = new FileReaderUtil();
        }
        return instance;
    }

    public List<Student> readStudentsFromFile(String fileName) throws IOException {

        logger.info("Начало чтения из файла, считывание файла информации о студентах: " + fileName);

        List<Student> studentsList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Студенты");

            Iterator<Row> rowIterator = sheet.iterator();

            // пропускаем заголовок
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String universityId = row.getCell(0).getStringCellValue();
                String fullName = row.getCell(1).getStringCellValue();
                int currentCourseNumber = (int) row.getCell(2).getNumericCellValue();
                float avgExamScore = (float) row.getCell(3).getNumericCellValue();

                Student student = new Student(fullName, universityId, currentCourseNumber, avgExamScore);

                studentsList.add(student);
            }

            logger.info("Считывание информации о студентах закончено. Количество записей: " + studentsList.size());

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Ошибка при чтении файла: {0}\n{1}", new Object[]{fileName, ex});
            throw ex;
        }

        return studentsList;
    }

    public List<University> readUniversitiesFromFile(String fileName) throws IOException {

        logger.info("Считывание информации об университетах: " + fileName);

        List<University> universitiesList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
        Sheet sheet = workbook.getSheet("Университеты");

        Iterator<Row> rowIterator = sheet.iterator();

        // пропускаем заголовок
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String id = row.getCell(0).getStringCellValue();
            String fullName = row.getCell(1).getStringCellValue();
            String shortName = row.getCell(2).getStringCellValue();
            int yearOfFoundation = (int) row.getCell(3).getNumericCellValue();

            StudyProfile mainProfile = StudyProfile.valueOf(row.getCell(4).getStringCellValue());

            University university = new University(id, fullName, shortName, yearOfFoundation, mainProfile);
            universitiesList.add(university);

            logger.fine("Считывание информации об университетах: " + university);
        }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Ошибка при чтении файла: {0}\n{1}", new Object[]{fileName, ex});
            throw ex;
        }

        return universitiesList;
    }
}
