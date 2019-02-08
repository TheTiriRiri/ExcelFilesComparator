package ExcelFileProgram.excel;

import java.io.File;

public class ExcelFileControllerImpl {

    private ExcelFileImpl excelFileImpl;
    private int openAtSheet = 0;

    private File file;

    public ExcelFileControllerImpl(File file) {
        this.file = file;
    }

    public ExcelFileImpl getExcelFileImpl() {
        return excelFileImpl;
    }

    public void createDataTable() {
        excelFileImpl = new ExcelFileImpl(file);
        excelFileImpl.openFileAtSheet(openAtSheet);
        excelFileImpl.createTab();
        excelFileImpl.convertDataToTab();
    }

    public void createDataList(){
        excelFileImpl = new ExcelFileImpl(file);
        excelFileImpl.openFileAtSheet(openAtSheet);
        excelFileImpl.createList();
        excelFileImpl.convertDataToList();
    }

}
/**
 * for (int k = 0; k < 25; k++) {
 * for (int h = 0; h < 7; h++) {
 * System.out.print(tab[k][h] + "; ");
 * }
 * System.out.println();
 * }
 **/