package ExcelFileProgram.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExcelFileImpl implements ExcelFile {

    private File file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFCell cell;
    private String[][] tab;
    private List<String> list;

    public ExcelFileImpl(File file) {
        this.file = file;
    }

    public String[][] getTab() {
        return tab;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public int getLastRowNumber() {
        return sheet.getLastRowNum() + 1;
    }

    @Override
    public int getLastCellNumber() {
        XSSFSheet tmpSheet = sheet;
        Iterator<Row> rowIt = tmpSheet.iterator();
        Row row = rowIt.next();
        row = rowIt.next();
        row = rowIt.next();
        row = rowIt.next();
        return row.getLastCellNum();//TODO
    }

    @Override
    public String getCellValue(int row, int column) {
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    @Override
    public void createTab() {
        int numberRows = getLastRowNumber();
        int numberCells = getLastCellNumber();
        tab = new String[numberRows][numberCells];
    }

    @Override
    public void convertDataToTab() {
        int i = -1;
        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            i++;
            int j = 0;
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                tab[i][j] = cell.toString();
                j++;
            }
        }
    }

    @Override
    public void createList() {
        list = new LinkedList<>();
    }

    @Override
    public void convertDataToList() {

        Iterator<Row> rowIt = sheet.iterator();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                stringBuilder.append(cell.toString() + "; ");
            }
            list.add(stringBuilder.toString());
        }
    }

    @Override
    public void openFileAtSheet(int numberSheet) {
        try {
            FileInputStream source = new FileInputStream(file);
            workbook = new XSSFWorkbook(source);
            sheet = workbook.getSheetAt(numberSheet);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveFile(String directory) {

    }
}

//https://www.youtube.com/watch?v=nmum2jEwOUA