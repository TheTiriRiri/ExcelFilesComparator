package ExcelFileProgram.comparator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileComparator {

    private String mainCellName = "Name"; //TODO
    private String helpfulCellName1;
    private String helpfulCellName2;
    private int mainCellNumber;
    private int helpfulCellNumber1;
    private int helpfulCellNumber2;
    private int titleRowNumber = 0;

    private List<String> baseList;
    private List<String> toCompareList;
    private List<String> linesListIsOk;
    private List<String> linesListIsNotOk;
    private List<String> extraLinesFromBaseExcelFileList;
    private List<String> extraLinesFromToComapreExcelFileList;

    public FileComparator(List<String> baseList, List<String> toCompareList) {
        this.baseList = baseList;
        this.toCompareList = toCompareList;
    }

    public void setMainCellNumberByCellName() {
        splitLineBySemicolon();
    }

    private String[] splitLineBySemicolon() {
        String[] parts = baseList.get(titleRowNumber).split(";");
        return parts;
    }

    private String[] splitLineBySemicolon(String line) {
        String[] parts = line.split(";");
        return parts;
    }
}