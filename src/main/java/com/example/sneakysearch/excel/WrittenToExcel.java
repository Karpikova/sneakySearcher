package com.example.sneakysearch.excel;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.found.FoundFromWeb;
import com.example.sneakysearch.found.FoundFromWebByAllTypoVariantsOfPhrase;
import com.example.sneakysearch.result.PurchaseObject;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.file.FileName;
import com.example.sneakysearch.result.file.FileNameInDownloadFolder;
import com.example.sneakysearch.typos.wrongbutton.RussianKeyboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class WrittenToExcel implements WrittenToFile {
    private final FoundFromWeb foundFromWebByAllTypoVariantsOfWord;
    private final FileName fileName;
    private final List<Header> headers;
    private final Workbook workbook;
    private static String RESULT_SHEET_NAME = "Result";
    private static String MISTAKE_SHEET_NAME = "Mistake";
    private static final Logger LOGGER = LogManager.getLogger(WrittenToExcel.class);

    public WrittenToExcel(FoundFromWeb foundFromWebByAllTypoVariantsOfWord, FileName fileName, List<Header> headers, Workbook workbook) {
        this.foundFromWebByAllTypoVariantsOfWord = foundFromWebByAllTypoVariantsOfWord;
        this.fileName = fileName;
        this.headers = headers;
        this.workbook = workbook;
    }

    public WrittenToExcel(FoundFromWeb foundFromWebByAllTypoVariantsOfWord, List<Header> headers, String phrase) {
        this(foundFromWebByAllTypoVariantsOfWord,
                new FileNameInDownloadFolder(phrase),
                headers,
                new XSSFWorkbook());
    }

    public WrittenToExcel(List<Header> headers, String phrase, LocalDate filterDate) {
        this(new FoundFromWebByAllTypoVariantsOfPhrase(phrase, filterDate, new RussianKeyboard()), headers, phrase);
    }

    @Override
    public void writeToFile() throws SneakySearchException {
        try {
            final Result result = foundFromWebByAllTypoVariantsOfWord.foundFromWeb();
            final Set<ResultLink> resultLinks = result.resultLinks();
            LOGGER.info("Result contains links: " + resultLinks.size());
            createLinksSheet(resultLinks);
        } catch (SneakySearchException e) {
            createMistakesSheet(e);
        }

        try (OutputStream outputStream = new FileOutputStream(fileName.value())) {
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new SneakySearchException("FileOutputStream problem");
        }
    }

    private void createLinksSheet(Set<ResultLink> resultLinks) {
        final Sheet sheet = workbook.createSheet(RESULT_SHEET_NAME);
        createHeaderRow(sheet, headers);
        createLinkRows(resultLinks, sheet);
    }

    private void createLinkRows(Set<ResultLink> resultLinks, Sheet sheet) {
        final AtomicInteger rowIndex = new AtomicInteger(1);
        resultLinks.forEach(rl -> createNewLinkRow(sheet, rowIndex.getAndIncrement(), rl));
    }

    private void createMistakesSheet(Exception e) {
        final Sheet sheet = workbook.createSheet(MISTAKE_SHEET_NAME);
        final StackTraceElement[] stackTrace = e.getStackTrace();
        createNewMistakeRow(e.getMessage(), sheet, 0);
        printStackTraceToSheet(stackTrace, sheet);
    }

    private void createHeaderRow(Sheet sheet, List<Header> headers) {
        final Row headerRow = sheet.createRow(0);
        final CellStyle headerCellStyle = createHeaderStyle();

        for (int i = 0; i < headers.size(); i++) {
            final Header header = headers.get(i);
            final Cell cell = headerRow.createCell(i);
            cell.setCellValue(header.caption());
            cell.setCellStyle(headerCellStyle);
            sheet.setColumnWidth(i, header.size());
        }
    }

    private void printStackTraceToSheet(StackTraceElement[] stackTrace, Sheet sheet) {
        final AtomicInteger iRow = new AtomicInteger(1);
        Arrays.stream(stackTrace).forEach(ste -> createNewMistakeRow(ste.toString(), sheet, iRow.getAndIncrement()));
    }

    private void createNewLinkRow(Sheet sheet, int rowIndex, ResultLink resultLink) {
        final Row row = sheet.createRow(rowIndex);

        final PurchaseObject purchaseObject = resultLink.purchaseObject();

        Cell cell = row.createCell(0);
        cell.setCellValue(resultLink.searchedPhrase());

        cell = row.createCell(1);
        cell.setCellValue(resultLink.link());

        cell = row.createCell(2);
        cell.setCellValue(purchaseObject.number());

        cell = row.createCell(3);
        cell.setCellValue(purchaseObject.name());

        cell = row.createCell(4);
        cell.setCellValue(purchaseObject.customer());

        cell = row.createCell(5);
        cell.setCellValue(purchaseObject.formattedDate());
    }

    private void createNewMistakeRow(String text, Sheet sheet, int iRow) {
        final Row row = sheet.createRow(iRow);
        final Cell cell = row.createCell(0);
        cell.setCellValue(text);
    }


    private void setAutoSizeColumns(Sheet sheet, Set<ResultLink> resultLinks) {
        for (int i = 0; i < resultLinks.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private CellStyle createHeaderStyle() {
        final CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        return headerCellStyle;
    }
}
