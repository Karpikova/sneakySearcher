package com.example.sneakysearch;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

public final class WrittenToFileMy implements WrittenToFile {
    private final FoundFromWebByOneWordAllTypoVariants foundFromWebByOneWordAllTypoVariants;

    public WrittenToFileMy(FoundFromWebByOneWordAllTypoVariants foundFromWebByOneWordAllTypoVariants) {
        this.foundFromWebByOneWordAllTypoVariants = foundFromWebByOneWordAllTypoVariants;
    }

    public WrittenToFileMy(String word) {
        this(new FoundFromWebByOneWordAllTypoVariantsMy(word));
    }

    @Override
    public void writeToFile() {
        Set<ResultLink> result = foundFromWebByOneWordAllTypoVariants.foundFromWeb();
        String[] headers  = new String[5];

        headers[0] = "Что искали";
        headers[1] = "Объект закупки";
        headers[2] = "Номер";
        headers[3] = "Клиент";
        headers[4] = "Ссылка";
        exportExcelFile(result, headers, "C:\\Users\\maria\\Documents\\QWE.XLS");
        System.out.println(result.size());
    }

    public void exportExcelFile(Set<ResultLink> resultLinks, String[] headers, String fileName) {
        // create a new Workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new Sheet named "Contacts"
        Sheet sheet = workbook.createSheet("Contacts");

        // Create header row
        createHeaderRow(workbook, sheet, headers);

        // Create rows
        int rowIndex = 1;
        for (ResultLink resultLink : resultLinks) {
            createNewRow(workbook, sheet, rowIndex, resultLink);
            rowIndex++;
        }

        // Adjusts 3 columns to set the width to fit the contents.
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        // Write to file
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create header row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param headers the headers text
     */
    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    /**
     * Create a new row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param rowIndex the index of row to create
     * @param resultLink the Contact object which represent information to write to row.
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, ResultLink resultLink) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(resultLink.searchedWord());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(resultLink.purchaseObject());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(resultLink.number());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue(resultLink.customer());
        cell.setCellStyle(cellStyle);


        cell = row.createCell(4);
        cell.setCellValue(resultLink.link());
        cell.setCellStyle(cellStyle);

    }

}
