package ExcelFileProgram.listFilesToCompare;

import java.io.File;

public interface FileToCompareListController {

    File getFileToCompare(String nameFileFromQueue) throws Exception;

    Boolean ListNotEmpty();
}
