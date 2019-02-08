package ExcelFileProgram.listFilesToCompare;

import java.io.File;
import java.util.List;

public class FileToCompareListControllerImpl implements FileToCompareListController {

    private File fileToCompare = null;
    private List<File> fileToCompareList;

    public FileToCompareListControllerImpl(List<File> fileToCompareList) {
        this.fileToCompareList = fileToCompareList;
    }

    @Override
    public File getFileToCompare(String nameFileFromQueue) throws Exception {
        for (File fileItem : fileToCompareList) {
            if (fileItem.getName().equals(nameFileFromQueue))
                fileToCompare = fileItem;
        }
        if (fileToCompare == null) {
            throw new Exception();
        }
        return fileToCompare;
    }

    @Override
    public Boolean ListNotEmpty() {
        return fileToCompareList.size() > 0;
    }

}