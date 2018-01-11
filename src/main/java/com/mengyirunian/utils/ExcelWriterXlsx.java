package com.mengyirunian.utils;


import com.mengyirunian.annotation.CsvExport;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jiaxiayuan on 2017/8/2.
 * Excel 2007 工具类
 */
public class ExcelWriterXlsx {

    private String fileName;

    private XSSFWorkbook workbook;

    private OutputStream outputStream = null;

    private static Map<Class<?>, Field[]> fieldMap = new HashMap<>();

    public ExcelWriterXlsx() {
        workbook = new XSSFWorkbook();
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public XSSFSheet createSheet(String name) {
        return workbook.createSheet(name);
    }

    public XSSFSheet getSheetByName(String name) {
        return workbook.getSheet(name);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int writeData(String sheetName, ArrayList<ArrayList<String>> datalist, int index) {
        XSSFSheet sheet = getSheetByName(sheetName);
        if (sheet == null) {
            sheet = createSheet(sheetName);
        }
        for (int i = 0; i < datalist.size(); i++) {
            ArrayList<String> list = datalist.get(i);
            XSSFRow row = sheet.createRow(index++);
            for (int j = 0; j < list.size(); j++) {
                String data = list.get(j);
                row.createCell(j).setCellValue(data);
            }
        }
        return index;
    }

    public int writeHeadData(String sheetName, ArrayList<String> list) {
        XSSFSheet sheet = getSheetByName(sheetName);
        if (sheet == null) {
            sheet = createSheet(sheetName);
        }
        XSSFRow row = sheet.createRow(0);
        for (int j = 0; j < list.size(); j++) {
            String data = list.get(j);
            row.createCell(j).setCellValue(data);
        }
        return 1;
    }

    public int writeHeadData(String sheetName, String[] array) {
        XSSFSheet sheet = getSheetByName(sheetName);
        if (sheet == null) {
            sheet = createSheet(sheetName);
        }
        XSSFRow row = sheet.createRow(0);
        for (int j = 0; j < array.length; j++) {
            String data = array[j];
            row.createCell(j).setCellValue(data);
        }
        return 1;
    }

    public int writeData(String sheetName, String[] array, int index) {
        XSSFSheet sheet = getSheetByName(sheetName);
        if (sheet == null) {
            sheet = createSheet(sheetName);
        }
        XSSFRow row = sheet.createRow(index++);
        for (int j = 0; j < array.length; j++) {
            String data = array[j];
            row.createCell(j).setCellValue(data);
        }
        return index;
    }

    /**
     * 通过bean去写入excel表头
     */
    public ExcelWriterXlsx writeExcelHead(String sheetName, Class<?> clazz) {
        if (clazz == null) {
            return this;
        }
        List<String> list;
        Field[] fieldArray = getFields(clazz);
        list = new ArrayList<>(fieldArray.length);
        for (Field field : fieldArray) {
            CsvExport annotation = field.getAnnotation(CsvExport.class);
            if (annotation != null) {
                list.add(annotation.head());
            }
        }
        if (!list.isEmpty()) {
            String[] array = list.toArray(new String[list.size()]);
            writeHeadData(sheetName, array);
        }
        return this;
    }

    /**
     * 通过bean去写入excel数据
     */
    private ExcelWriterXlsx writeExcelData(String sheetName, Object obj, int index) {
        if (obj == null) {
            return this;
        }
        List<String> list;

        Field[] fieldArray = getFields(obj.getClass());

        list = new ArrayList<>(fieldArray.length);

        for (Field field : fieldArray) {
            field.setAccessible(true);
            try {
                Object object = field.get(obj);
                if (object instanceof Date) {
                    list.add(DateUtils.parse((Date) object));
                } else if (object instanceof BigDecimal) {
                    list.add(((BigDecimal) object).stripTrailingZeros().toPlainString());
                } else {
                    list.add(String.valueOf(object));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                list.add(StringUtils.EMPTY);
            }
        }
        if (!list.isEmpty()) {
            String[] array = list.toArray(new String[list.size()]);
            writeData(sheetName, array, index);
        }
        return this;
    }

    public ExcelWriterXlsx writeExcelData(String sheetName, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return this;
        }
        for (int i = 1; i <= list.size(); i++) {
            writeExcelData(sheetName, list.get(i - 1), i);
        }
        return this;
    }

    private Field[] getFields(Class<?> clazz) {
        Field[] fieldArray = fieldMap.get(clazz);

        if (fieldArray == null) {
            Field[] declaredFields = clazz.getDeclaredFields();

            List<Field> fields = new ArrayList<>(declaredFields.length);
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(CsvExport.class)) {
                    fields.add(field);
                }
            }

            fieldArray = fields.toArray(new Field[fields.size()]);

        }
        return fieldArray;
    }
}
