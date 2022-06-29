package filework;

import filework.entities.*;
import filework.storage.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileStorageConsoleApp<T extends Writable> {
    protected static final Scanner scanner = new Scanner(System.in);
    protected Storage<T> fileStorage;
    protected File directory;

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public void run() {
        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1":
                    writeIntoFile();
                    break;
                case "2":
                    readFromFile();
                    break;
                case "3":
                    deleteFile();
                    break;
                case "4":
                    showAllFiles();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("No such action exists");
                    break;
            }
        }
    }

    private void writeIntoFile() {
        T item = initializeEntity();
        try {
            LOGGER.info("File created: " + fileStorage.writeIntoFile(item, directory));
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    abstract T initializeEntity();

    protected int inputInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("You must enter integer number. Try again");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private void readFromFile() {
        System.out.println("Enter the file name: ");
        String filename = scanner.nextLine();
        try {
            T item = fileStorage.readFromFile(directory, filename);
            LOGGER.info("Entity: " + item.toString());
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    private void deleteFile() {
        System.out.println("Enter the file name:");
        String filename = scanner.nextLine();
        try {
            fileStorage.deleteFile(directory, filename);
            LOGGER.info("File " + filename + " deleted");
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    private static void printMenu() {
        System.out.println("1. Create object & write is into file\n" +
                "2. Read object from file\n" +
                "3. Delete file\n" +
                "4. Show list of files\n" +
                "0. Exit");
    }

    private void showAllFiles() {
        String[] listOfFiles = fileStorage.getListOfFiles(directory);
        if (listOfFiles.length < 1) {
            System.out.println("Directory is empty\n");
            return;
        }
        for (String filename : listOfFiles) {
            System.out.println(filename);
        }
    }

    protected void pathInitialisation() {
        directory = new File("Entities", fileStorage.getSubDir());
        directory.mkdirs();
    }
}