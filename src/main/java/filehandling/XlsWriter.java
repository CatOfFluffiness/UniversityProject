package filehandling;

import classes.Statistics;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class XlsWriter {

    private static final Logger logger = Logger.getLogger(XlsWriter.class.getName());

    public static void generateTableAndWriteToFile(List<Statistics> statisticsList, String filePath) {
        logger.info("Начало записи информации в файл");

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.createSheet("Statistics");
            logger.info("Создана таблица '" + sheet.getSheetName() + "'");

            // Создание строки с названиями столбцов
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);

            Cell profileHeaderCell = headerRow.createCell(0);
            profileHeaderCell.setCellValue("Профиль обучения");
            profileHeaderCell.setCellStyle(headerStyle);

            Cell avgExamScoreHeaderCell = headerRow.createCell(1);
            avgExamScoreHeaderCell.setCellValue("Средний балл");
            avgExamScoreHeaderCell.setCellStyle(headerStyle);

            Cell numberOfStudentsHeaderCell = headerRow.createCell(2);
            numberOfStudentsHeaderCell.setCellValue("Количество студентов");
            numberOfStudentsHeaderCell.setCellStyle(headerStyle);

            Cell numberOfUniversitiesHeaderCell = headerRow.createCell(3);
            numberOfUniversitiesHeaderCell.setCellValue("Количество университетов");
            numberOfUniversitiesHeaderCell.setCellStyle(headerStyle);

            Cell universityNamesHeaderCell = headerRow.createCell(4);
            universityNamesHeaderCell.setCellValue("Название университета");
            universityNamesHeaderCell.setCellStyle(headerStyle);

            // Заполняем информацией
            int rowNum = 1;
            for (Statistics statistics : statisticsList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(statistics.getProfile().toString());
                row.createCell(1).setCellValue(statistics.getAvgExamScore());
                row.createCell(2).setCellValue(statistics.getNumberOfStudents());
                row.createCell(3).setCellValue(statistics.getNumberOfUniversities());
                row.createCell(4).setCellValue(statistics.getUniversityNames());
            }

            // Авторазмер колонн
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Запись в файл
            workbook.write(outputStream);
            logger.info("Запись в файл успешно завершена");

        } catch (IOException e) {
            logger.warning("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}