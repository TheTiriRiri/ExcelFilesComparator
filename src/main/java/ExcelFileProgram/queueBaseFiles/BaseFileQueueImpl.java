package ExcelFileProgram.queueBaseFiles;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BaseFileQueueImpl implements BaseFileQueue {

    private Queue<File> fileQueue = new LinkedList<>();
    private List<File> filesInFolder;

    public BaseFileQueueImpl(List<File> filesInFolder) {
        this.filesInFolder = filesInFolder;
    }

    @Override
    public String getNameHeadQueue() {
        return fileQueue.peek().getName();
    }

    @Override
    public void buildQueue() {
        for (File file : filesInFolder)
            fileQueue.add(file);
    }

    @Override
    public void removeHeadQueue() {
        fileQueue.remove();
    }

    @Override
    public File viewHeadQueue() {
        return fileQueue.peek();
    }

    @Override
    public Integer sizeQueue() {
        return fileQueue.size();
    }
}