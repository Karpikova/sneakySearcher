package com.example.sneakysearch.excel;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.found.FoundFromWeb;
import com.example.sneakysearch.found.FoundFromWebByAllTypoVariantsOfWordMy;
import com.example.sneakysearch.result.PurchaseObject;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.file.FileName;
import com.example.sneakysearch.result.file.FileNameInDownloadFolder;
import com.example.sneakysearch.typos.RussianKeyboard;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public final class WrittenToExcel implements WrittenToFile {
    private final FoundFromWeb foundFromWebByAllTypoVariantsOfWord;
    private final FileName fileName;
    private final Headers headers;
    private final Workbook workbook;

    public WrittenToExcel(FoundFromWeb foundFromWebByAllTypoVariantsOfWord,
                          FileName fileName, Headers headers, Workbook workbook) {
        this.foundFromWebByAllTypoVariantsOfWord = foundFromWebByAllTypoVariantsOfWord;
        this.fileName = fileName;
        this.headers = headers;
        this.workbook = workbook;
    }

    public WrittenToExcel(String word, Headers headers) {
        this(new FoundFromWebByAllTypoVariantsOfWordMy(word, new RussianKeyboard()),
                new FileNameInDownloadFolder(word),
                headers,
                new XSSFWorkbook());
    }

    @Override
    public void writeToFile() throws SneakySearchException {
        try {
            Result result = foundFromWebByAllTypoVariantsOfWord.foundFromWeb();
            Set<ResultLink> resultLinks = result.resultLinks();
            createLinksSheet(resultLinks);
            System.out.println(resultLinks.size());
        } catch (SneakySearchException e) {
            createMistakesSheet(e);
        }


        try (OutputStream outputStream = new FileOutputStream(fileName.value())) {
            workbook.write(outputStream);
        } catch (Exception e) {
            System.out.println(e);
            throw new SneakySearchException("FileOutputStream problem");
        }

    }

    private void createLinksSheet(Set<ResultLink> resultLinks) {
        Sheet sheet = workbook.createSheet("Result");
        createHeaderRow(sheet, headers.headers());

        int rowIndex = 1;
        for (ResultLink resultLink : resultLinks) {
            createNewLinkRow(sheet, rowIndex, resultLink);
            rowIndex++;
        }

        setAutoSizeColumns(sheet, resultLinks);
    }

    private void createMistakesSheet(Exception e) {
        Sheet sheet = workbook.createSheet("Mistakes");
        StackTraceElement[] stackTrace = e.getStackTrace();
        printDetailedMessage(e, sheet);
        printStackTrace(stackTrace, sheet);
    }

    private void printDetailedMessage(Exception e, Sheet sheet) {
        createNewMistakeRow(e.getMessage(), sheet, 0);
    }

    private void printStackTrace(StackTraceElement[] stackTrace, Sheet sheet) {
        int iRow = 1;
        for (StackTraceElement ste : stackTrace) {
            createNewMistakeRow(ste.toString(), sheet, iRow);
            iRow++;
        }
    }

    private void createNewLinkRow(Sheet sheet, int rowIndex, ResultLink resultLink) {
        Row row = sheet.createRow(rowIndex);

        PurchaseObject purchaseObject = resultLink.purchaseObject();

        Cell cell = row.createCell(0);
        cell.setCellValue(resultLink.searchedWord());

        cell = row.createCell(1);
        cell.setCellValue(resultLink.link());

        cell = row.createCell(2);
        cell.setCellValue(purchaseObject.number());

        cell = row.createCell(3);
        cell.setCellValue(purchaseObject.name());

        cell = row.createCell(4);
        cell.setCellValue(purchaseObject.customer());
    }

    private void createNewMistakeRow(String text, Sheet sheet, int iRow) {
        Row row = sheet.createRow(iRow);
        Cell cell = row.createCell(0);
        cell.setCellValue(text);
    }


    private void createHeaderRow(Sheet sheet, List<String> headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = createHeaderStyle();

        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void setAutoSizeColumns(Sheet sheet, Set<ResultLink> resultLinks) {
        for (int i = 0; i < resultLinks.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private CellStyle createHeaderStyle() {
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        return headerCellStyle;
    }
}
