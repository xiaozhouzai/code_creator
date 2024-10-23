package com.example.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PoiReadUtils {
    public static List<Map<Integer, String>> readXls(InputStream inputStream) {
        return readXls(inputStream, 0);
    }

    public static List<Map<Integer, String>> readXls(InputStream inputStream, int headRowNumber) {
        try {
            Workbook workbook = new HSSFWorkbook(inputStream);
            List<Map<Integer, String>> result = new ArrayList<>();

            Sheet sheetAt = workbook.getSheetAt(0);
            // 跳过标题行
            for (int rowIndex = headRowNumber; rowIndex <= sheetAt.getLastRowNum(); rowIndex++) {
                Row row = sheetAt.getRow(rowIndex);
                if (row != null) {
                    Map<Integer, String> cellValues = new LinkedHashMap<>();
                    for (Cell cell : row) {
                        cellValues.put(cell.getColumnIndex(), getCellValueAsString(cell));
                    }
                    result.add(cellValues);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
