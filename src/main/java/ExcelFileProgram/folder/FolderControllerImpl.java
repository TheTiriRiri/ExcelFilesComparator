package ExcelFileProgram.folder;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FolderControllerImpl implements FolderController {

    private String directory;
    private List<File> filesInFolder;

    public FolderControllerImpl(String directory) {
        this.directory = directory;
        filesInFolder = new ArrayList<>();
    }

    @Override
    public List<File> getFilesInFolder() {
        return filesInFolder;
    }

    @Override
    public void collectFilesInFolder() {
        try {
            filesInFolder = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}