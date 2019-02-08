package ExcelFileProgram.folder;

import java.io.File;
import java.util.List;

public interface FolderController {

    List<File> getFilesInFolder();

    void collectFilesInFolder();
}
