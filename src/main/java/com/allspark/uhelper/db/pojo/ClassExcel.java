package com.allspark.uhelper.db.pojo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Pzr
 * @create 2023/2/10 - 21:09
 */

public class ClassExcel {
    String PATH = "C:\\Users\\44110\\Desktop\\software-learn\\3g-project\\uhelper\\";

    @Test
    public void testxls() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(PATH + "对抗2102.xls");


        Workbook workbook = new HSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(0);

        Cell cell = row.getCell(0);
        System.out.println(cell);
        fileInputStream.close();
    }

    @Test
    public void testxlsx() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(PATH + "对抗2102.xlsx");


        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(0);

        Cell cell = row.getCell(0);
        System.out.println(cell);
        fileInputStream.close();
    }
}
