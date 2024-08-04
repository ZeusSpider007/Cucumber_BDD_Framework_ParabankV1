package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtils {
    public static List<HashMap<String, String>> readExcelData(String filePath, String sheetName) {
        List<HashMap<String, String>> data = new ArrayList<>();
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentData = new HashMap<>();
                
                if (currentRow != null) {
                    for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                        Cell headerCell = headerRow.getCell(j);
                        Cell currentCell = currentRow.getCell(j);
                        
                        String header = (headerCell != null) ? headerCell.getStringCellValue() : "";
                        String value = (currentCell != null) ? getCellValueAsString(currentCell) : "";
                        
                        currentData.put(header, value);
                    }
                    
                    data.add(currentData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Convert numeric values to an integer if no decimal part is expected
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        // No decimal part
                        return String.valueOf((int) numericValue);
                    } else {
                        // Decimal part exists
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // Or handle as needed
            default:
                return "";
        }
    }

}
