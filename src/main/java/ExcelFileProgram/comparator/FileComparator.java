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
    private String[] titleLineParts;
    private List<String> itemsByMainCell;

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

    public int getMainCellNumber() {
        return mainCellNumber;
    }

    public void selectMainCellNumberByCellName() {
        titleLineParts = splitLineBySemicolon();
        mainCellNumber = getCellNumber();
    }

    private String[] splitLineBySemicolon() {
        return baseList.get(titleRowNumber).split(";");
    }

    private String[] splitLineBySemicolon(String line) {
        return line.split(";");
    }

    private int getCellNumber() {
        int i = 0;
        for (String item : titleLineParts) {
            if (item.contains(mainCellName)) {
                break;
            } else {
                i++;
            }
        }
        return i;
    }

    public void getItemsByMainCell() {
        itemsByMainCell = new LinkedList<>();
        for (String item : baseList) {
            String[] splittedLine = splitLineBySemicolon(item);
            itemsByMainCell.add(splittedLine[mainCellNumber]);
        }
        System.out.println(Arrays.asList(itemsByMainCell));
    }
}