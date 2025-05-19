package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {

    public static Object[][] readExcelData(String fileName, String sheetName) throws IOException {
        // Load the file using ClassLoader from the resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("File not found in resources folder: " + fileName);
        }

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        // Hardcoding to 2 columns: username and password
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            data[i - 1][0] = row.getCell(0) != null ? row.getCell(0).toString() : "";
            data[i - 1][1] = row.getCell(1) != null ? row.getCell(1).toString() : "";
        }

        workbook.close();
        return data;
    }
}
