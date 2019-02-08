package ExcelFileProgram.queueBaseFiles;

import java.io.File;
import java.util.List;

public class BaseFileQueueControllerImpl implements BaseFileQueueController {

    private BaseFileQueue baseFileQueue;

    public BaseFileQueueControllerImpl(List<File> filesInFolder) {
        baseFileQueue = new BaseFileQueueImpl(filesInFolder);
        baseFileQueue.buildQueue();
    }

    @Override
    public File getFileToPerform() throws Exception {
        File tmpFileToPerform = null;
        tmpFileToPerform = baseFileQueue.viewHeadQueue();
        if (tmpFileToPerform == null)
            throw new Exception();
        return tmpFileToPerform;
    }

    @Override
    public String getNameFileToPerform(){
        return baseFileQueue.getNameHeadQueue();
    }

    @Override
    public void setNext() {
        baseFileQueue.removeHeadQueue();
    }

    @Override
    public Boolean queueNotEmpty() {
        return baseFileQueue.sizeQueue() > 0;
    }
}
