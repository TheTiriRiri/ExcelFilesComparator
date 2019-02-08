package ExcelFileProgram.queueBaseFiles;

import java.io.File;
import java.util.Queue;

public interface BaseFileQueue {

    String getNameHeadQueue();

    void buildQueue();

    void removeHeadQueue();

    File viewHeadQueue();

    Integer sizeQueue();
}