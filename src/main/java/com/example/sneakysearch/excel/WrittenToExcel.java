package com.example.sneakysearch.excel;

import com.example.sneakysearch.FoundFromWebByOneWordAllTypoVariants;
import com.example.sneakysearch.FoundFromWebByOneWordAllTypoVariantsMy;
import com.example.sneakysearch.PurchaseObject;
import com.example.sneakysearch.ResultLink;
import com.example.sneakysearch.WrittenToFile;
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
    private final FoundFromWebByOneWordAllTypoVariants foundFromWebByOneWordAllTypoVariants;
    private final String fileName;
    private final Headers headers;
    private final Workbook workbook;

    public WrittenToExcel(FoundFromWebByOneWordAllTypoVariants foundFromWebByOneWordAllTypoVariants,
                          String fileName, Headers headers, Workbook workbook) {
        this.foundFromWebByOneWordAllTypoVariants = foundFromWebByOneWordAllTypoVariants;
        this.fileName = fileName;
        this.headers = headers;
        this.workbook = workbook;
    }

    public WrittenToExcel(String word, String fileName, Headers headers) {
        this(new FoundFromWebByOneWordAllTypoVariantsMy(word), fileName, headers, new XSSFWorkbook());
    }

    @Override
    public void writeToFile() {
        Set<ResultLink> resultLinks = foundFromWebByOneWordAllTypoVariants.foundFromWeb();
        Sheet sheet = workbook.createSheet("Result");
        createHeaderRow(workbook, sheet, headers.headers());

        int rowIndex = 1;
        for (ResultLink resultLink : resultLinks) {
            createNewRow(sheet, rowIndex, resultLink);
            rowIndex++;
        }

        setAutoSizeColumns(sheet, resultLinks);

        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (Exception e) {
            System.out.println(e);
            //throw new SneakySearchException(e); TODO отдать его пользователю
        }

        System.out.println(resultLinks.size());
    }

    private void setAutoSizeColumns(Sheet sheet, Set<ResultLink> resultLinks) {
        for (int i = 0; i < resultLinks.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createHeaderRow(Workbook workbook, Sheet sheet, List<String> headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = createHeaderStyle(workbook);

        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void createNewRow(Sheet sheet, int rowIndex, ResultLink resultLink) {
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

    private CellStyle createHeaderStyle(Workbook workbook) {
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
