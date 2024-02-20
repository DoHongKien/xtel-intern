package com.example.democrud.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private static final String FONT_NAME = "Times New Roman";

    public static CellStyle styleHeader(Workbook workbook) {
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight(16);
        font.setBold(true);
        styleHeader.setFont(font);
        return styleHeader;
    }

    public static CellStyle styleTitle(Workbook workbook) {
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight(12);
        font.setBold(true);
        styleHeader.setFont(font);
        return styleHeader;
    }

    public static CellStyle styleContent(Workbook workbook) {
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.RIGHT);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight(13);
        font.setBold(false);
        styleHeader.setFont(font);
        return styleHeader;
    }
}
