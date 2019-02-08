package ExcelFileProgram.comparator;

import ExcelFileProgram.excel.ExcelFileImpl;

public class FileComparatorController {

    FileComparator fileComparator;

    public FileComparatorController(ExcelFileImpl baseExcelFileImpl, ExcelFileImpl toCompareExcelFileImpl) {
        this.fileComparator = new FileComparator(baseExcelFileImpl.getList(), toCompareExcelFileImpl.getList());
    }

    public void prepareListAssupmtions() {
        fileComparator.setMainCellNumberByCellName();
    }
}