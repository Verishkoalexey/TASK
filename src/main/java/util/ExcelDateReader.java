package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Waste;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelDateReader {
    public static ObservableList<Waste> readFromExcel(String file) throws IOException {
            ObservableList<Waste> wasteList = FXCollections.observableArrayList();
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet myExcelSheet = myExcelBook.getSheet("Классификатор отходов");
            for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = myExcelSheet.getRow(i);
                Waste newWaste = new Waste();
                if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    int codeWaste = Integer.parseInt(String.valueOf(row.getCell(0).getNumericCellValue()).replace(".0", ""));
                    newWaste.setCodeWaste(codeWaste);
                }

                if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String nameWaste = row.getCell(1).getStringCellValue();
                    newWaste.setNameWaste(nameWaste);
                }

                if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String degreeOfDanger = row.getCell(2).getStringCellValue();
                    newWaste.setDegreeOfDanger(degreeOfDanger);
                }

                if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String hazardClass = row.getCell(3).getStringCellValue();
                    newWaste.setHazardClass(hazardClass);
                }

                if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String kindOfActivity = row.getCell(4).getStringCellValue();
                    newWaste.setKindOfActivity(kindOfActivity);
                }

                if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String educationStandard = row.getCell(5).getStringCellValue();
                    newWaste.setEducationStandard(educationStandard);
                }
                wasteList.add(newWaste);
            }
            myExcelBook.close();
            return wasteList;
    }

    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(String file, ObservableList<Waste> wastesList) throws IOException{
        XSSFWorkbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Классификатор отходов");

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Код отхода");
        row.createCell(1).setCellValue("Наименование");
        row.createCell(2).setCellValue("Степень опасности");
        row.createCell(3).setCellValue("Класс опасности");
        row.createCell(4).setCellValue("Вид деятельности");
        row.createCell(5).setCellValue("Норматив образования");

        for (Waste waste : wastesList) {
            createSheetHeader(sheet, ++rowNum, waste);
        }

        book.write(new FileOutputStream(file));
        System.out.println("Excel файл успешно создан!");
        book.close();
    }

    private static void createSheetHeader(Sheet sheet, int rowNum, Waste waste) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(waste.getCodeWaste());
        row.createCell(1).setCellValue(waste.getNameWaste());
        row.createCell(2).setCellValue(waste.getDegreeOfDanger());
        row.createCell(3).setCellValue(waste.getHazardClass());
        row.createCell(4).setCellValue(waste.getKindOfActivity());
        row.createCell(5).setCellValue(waste.getEducationStandard());
    }
}
