package com.example.demo.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;

public class ExportUtils {

//    public static void mergeCellsByCondition(Sheet sheet, List<Map<String,String>> users) {
//        Set<CellRangeAddress> mergedRegions = new HashSet<>();
//        int startRowIndex = 0;
//        int endRowIndex = 0;
//
//        for (int i = 1; i <= users.size(); i++) {
//            if (i < users.size()) {
//                Map<String,String> currentUser = users.get(i);
//                Map<String,String> previousUser = users.get(i - 1);
//
//                if (currentUser.get("姓名").equals(previousUser.get("姓名")) && Objects.equals(currentUser.get("年龄"), previousUser.get("年龄"))) {
//                    endRowIndex = i;
//                } else {
//                    handleMerge(sheet, mergedRegions, startRowIndex, endRowIndex);
//                    startRowIndex = endRowIndex = i;
//                }
//            } else {
//                handleMerge(sheet, mergedRegions, startRowIndex, endRowIndex);
//            }
//        }
//    }
//
//    private static void handleMerge(Sheet sheet, Set<CellRangeAddress> mergedRegions, int startRowIndex, int endRowIndex) {
//        if (startRowIndex != endRowIndex) {
//            // 合并姓名单元格
//            CellRangeAddress nameRange = CellRangeAddress.valueOf("$A$" + (startRowIndex + 1) + ":$A$" + (endRowIndex + 1));
//            if (!mergedRegions.contains(nameRange)) {
//                sheet.addMergedRegion(nameRange);
//                mergedRegions.add(nameRange);
//            }
//
//            // 合并年龄单元格
//            CellRangeAddress ageRange = CellRangeAddress.valueOf("$B$" + (startRowIndex + 1) + ":$B$" + (endRowIndex + 1));
//            if (!mergedRegions.contains(ageRange)) {
//                sheet.addMergedRegion(ageRange);
//                mergedRegions.add(ageRange);
//            }
//        }
//    }

    public static void mergeCellsByCondition(Sheet sheet, List<Map<String,String>> users) {
        Map<Integer, Integer> mergeStarts = new HashMap<>();
        Set<CellRangeAddress> mergedRegions = new HashSet<>();

        for (int i = 1; i < users.size(); i++) {
            Map<String,String> currentUser = users.get(i);
            Map<String,String> previousUser = users.get(i - 1);

            if (currentUser.get("姓名").equals(previousUser.get("姓名")) && Objects.equals(currentUser.get("年龄"), previousUser.get("年龄"))) {
                if (!mergeStarts.containsKey(i - 1)) {
                    mergeStarts.put(i - 1, i);
                }
            } else {
                if (mergeStarts.containsKey(i - 1)) {
                    handleMerge(sheet, mergeStarts, mergedRegions, i - 1);
                }
            }
        }

        // 处理最后一个合并区间
        if (mergeStarts.containsKey(users.size() - 1)) {
            handleMerge(sheet, mergeStarts, mergedRegions, users.size() - 1);
        }
    }

    private static void handleMerge(Sheet sheet, Map<Integer, Integer> mergeStarts, Set<CellRangeAddress> mergedRegions, int endIndex) {
        int startIndex = mergeStarts.get(endIndex);

        // 合并姓名单元格
        CellRangeAddress nameRange = CellRangeAddress.valueOf("$A$" + (startIndex + 1) + ":$A$" + (endIndex + 1));
        if (!mergedRegions.contains(nameRange)) {
            sheet.addMergedRegion(nameRange);
            mergedRegions.add(nameRange);
        }

        // 合并年龄单元格
        CellRangeAddress ageRange = CellRangeAddress.valueOf("$B$" + (startIndex + 1) + ":$B$" + (endIndex + 1));
        if (!mergedRegions.contains(ageRange)) {
            sheet.addMergedRegion(ageRange);
            mergedRegions.add(ageRange);
        }
    }

}
