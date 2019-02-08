package ExcelFileProgram.excel;

public interface ExcelFile {

    int getLastRowNumber();

    int getLastCellNumber();

    String getCellValue(int row, int column);

    void createTab();

    void convertDataToTab();

    void createList();

    void convertDataToList();

    void openFileAtSheet(int numberSheet);

    void saveFile(String directory);

}
