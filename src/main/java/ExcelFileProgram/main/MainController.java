package ExcelFileProgram.main;

import ExcelFileProgram.comparator.FileComparatorController;
import ExcelFileProgram.excel.ExcelFileControllerImpl;
import ExcelFileProgram.folder.FolderController;
import ExcelFileProgram.folder.FolderControllerImpl;
import ExcelFileProgram.listFilesToCompare.FileToCompareListControllerImpl;
import ExcelFileProgram.queueBaseFiles.BaseFileQueueControllerImpl;

import java.io.File;

public class MainController {

// linux path    private static String firstFileDirectory = "/home/konradk/Documents/KonradSoftware/6666/v0011/";
// linux path    private static String secondFileDirectory = "/home/konradk/Documents/KonradSoftware/6666/v0021/";

    private static String firstFileDirectory = "C:\\excelComp\\old";    // windows path
    private static String secondFileDirectory = "C:\\excelComp\\new";   // windows path

    private FolderController serverOneFolderController;
    private FolderController serverTwoFolderController;
    private BaseFileQueueControllerImpl serverOneQueueControllerImpl;
    private FileToCompareListControllerImpl serverTwoListControllerImpl;
    private ExcelFileControllerImpl serverOneFileControllerImpl;
    private ExcelFileControllerImpl serverTwoFileControllerImpl;
    private FileComparatorController fileComparatorController;
    private File serverOneFile;
    private File serverTwoFile;

    public MainController() {
        serverOneFolderController = new FolderControllerImpl(firstFileDirectory);
        serverTwoFolderController = new FolderControllerImpl(secondFileDirectory);
    }

    public void run() throws Exception {
        collectFilesInBothFolders();
        initializeQueueFiles();
        initializeListFilesToCompare();

        while (nextToCompareIsOk()) {
            setFilesToComparison();
            convertFilesToListStructure();
//            compareConvertedLists();


            ////////////////
            serverOneQueueControllerImpl.setNext();
            System.out.println("**********************");
        }
    }

    private void collectFilesInBothFolders() {
        serverOneFolderController.collectFilesInFolder();
        serverTwoFolderController.collectFilesInFolder();
    }

    private void initializeQueueFiles() {
        serverOneQueueControllerImpl = new BaseFileQueueControllerImpl(serverOneFolderController.getFilesInFolder());
    }

    private void initializeListFilesToCompare() {
        serverTwoListControllerImpl = new FileToCompareListControllerImpl(serverTwoFolderController.getFilesInFolder());
    }

    private Boolean nextToCompareIsOk() {
        return serverOneQueueControllerImpl.queueNotEmpty() && serverTwoListControllerImpl.ListNotEmpty();
    }

    private void setFilesToComparison() throws Exception {
        serverOneFile = serverOneQueueControllerImpl.getFileToPerform();
        serverTwoFile = serverTwoListControllerImpl.getFileToCompare(serverOneQueueControllerImpl.getNameFileToPerform());
    }

    private void convertFilesToListStructure() {
        serverOneFileControllerImpl = new ExcelFileControllerImpl(serverOneFile);
        serverTwoFileControllerImpl = new ExcelFileControllerImpl(serverTwoFile);
        serverOneFileControllerImpl.createDataList();

        serverTwoFileControllerImpl.createDataList();
    }

    private void compareConvertedLists() {
        fileComparatorController = new FileComparatorController(
                serverOneFileControllerImpl.getExcelFileImpl(),
                serverTwoFileControllerImpl.getExcelFileImpl());
        fileComparatorController.prepareListAssumptions();
    }
}