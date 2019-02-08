package ExcelFileProgram.queueBaseFiles;

import java.io.File;

public interface BaseFileQueueController {
    File getFileToPerform() throws Exception;

    String getNameFileToPerform();

    void setNext();

    Boolean queueNotEmpty();
}
