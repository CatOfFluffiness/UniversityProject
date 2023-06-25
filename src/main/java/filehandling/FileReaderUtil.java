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


public class FileReaderUtil {

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

        List<Student> studentsList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
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

        workbook.close();
        fis.close();

        return studentsList;
    }

    public List<University> readUniversitiesFromFile(String fileName) throws IOException {

        List<University> universitiesList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
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
        }

        workbook.close();
        fis.close();

        return universitiesList;
    }
}
