package ExcelFileProgram.main;

import ExcelFileProgram.comparator.FileComparatorController;
import ExcelFileProgram.excel.ExcelFileControllerImpl;
import ExcelFileProgram.folder.FolderController;
import ExcelFileProgram.folder.FolderControllerImpl;
import ExcelFileProgram.listFilesToCompare.FileToCompareListControllerImpl;
import ExcelFileProgram.queueBaseFiles.BaseFileQueueControllerImpl;

import java.io.File;

public class MainController {

    private static String firstFileDirectory = "/home/konradk/Documents/KonradSoftware/6666/v0011/";
    private static String secondFileDirectory = "/home/konradk/Documents/KonradSoftware/6666/v0021/";

    private FolderController firstFileFolderController;
    private FolderController secondFileFolderController;
    private BaseFileQueueControllerImpl baseFileQueueControllerImpl;
    private FileToCompareListControllerImpl fileToCompareListControllerImpl;
    private ExcelFileControllerImpl baseExcelFileControllerImpl;
    private ExcelFileControllerImpl toCompareExcelFileControllerImpl;
    private FileComparatorController fileComparatorController;
    private File fileFromQueue;
    private File fileToCompare;

    public MainController() {
        firstFileFolderController = new FolderControllerImpl(firstFileDirectory);
        secondFileFolderController = new FolderControllerImpl(secondFileDirectory);
    }

    public void run() throws Exception {
        collectFilesInBothFolders();
        initializeQueueFiles();
        initializeListFilesToCompare();

        while (nextToCompareIsOk()) {
            setFilesToComparison();
            convertFilesToListStructure();
            compareConvertedLists();


            ////////////////
            baseFileQueueControllerImpl.setNext();
            System.out.println("**********************");
        }
    }

    private void collectFilesInBothFolders() {
        firstFileFolderController.collectFilesInFolder();
        secondFileFolderController.collectFilesInFolder();
    }

    private void initializeQueueFiles() {
        baseFileQueueControllerImpl = new BaseFileQueueControllerImpl(firstFileFolderController.getFilesInFolder());
    }

    private void initializeListFilesToCompare() {
        fileToCompareListControllerImpl = new FileToCompareListControllerImpl(secondFileFolderController.getFilesInFolder());
    }

    private Boolean nextToCompareIsOk() {
        return baseFileQueueControllerImpl.queueNotEmpty() && fileToCompareListControllerImpl.ListNotEmpty();
    }

    private void setFilesToComparison() throws Exception {
        fileFromQueue = baseFileQueueControllerImpl.getFileToPerform();
        fileToCompare = fileToCompareListControllerImpl.getFileToCompare(baseFileQueueControllerImpl.getNameFileToPerform());
    }

    private void convertFilesToListStructure() {
        baseExcelFileControllerImpl = new ExcelFileControllerImpl(fileFromQueue);
        baseExcelFileControllerImpl.createDataList();
        toCompareExcelFileControllerImpl = new ExcelFileControllerImpl(fileToCompare);
        toCompareExcelFileControllerImpl.createDataList();
    }

    private void compareConvertedLists() {
        fileComparatorController = new FileComparatorController(
                baseExcelFileControllerImpl.getExcelFileImpl(),
                toCompareExcelFileControllerImpl.getExcelFileImpl());
        fileComparatorController.prepareListAssumptions();
    }
}