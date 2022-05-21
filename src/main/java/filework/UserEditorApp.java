package filework;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserEditorApp {
    private static final Scanner scanner = new Scanner(System.in);
    private String directory;
    private UserFileWorker fileWorker = new UserFileWorker();
    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public void run() {
        pathInitialisation();
        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1":
                    writeUserIntoFile();
                    break;
                case "2":
                    readUserFromFile();
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

    private void deleteFile() {
        System.out.println("Enter the file name:");
        String filename = scanner.nextLine();
        try {
            fileWorker.deleteUserFile(directory, filename);
            LOGGER.info("File " + filename + " deleted");
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    private void readUserFromFile() {
        try {
            System.out.println("Enter the file name: ");
            String filename = scanner.nextLine();
            User user = fileWorker.readUserFromFile(directory, filename);
            LOGGER.info("User: " + user.toString());
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    private void writeUserIntoFile() {
        User user = userInitialisation();
        try {
            LOGGER.info("File created: " + fileWorker.writeUserIntoFile(user, directory));
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }

    private User userInitialisation() {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Nickname: ");
        String nickname = scanner.nextLine();
        System.out.println("Role: ");
        String role = scanner.nextLine();
        System.out.println("Age: ");
        int age = inputInt();
        System.out.println("ID: ");
        int id = inputInt();
        scanner.nextLine();
        return new User(name, nickname, role, age, id);
    }

    private static void printMenu() {
        System.out.println("1. Create user & write is into file\n" +
                "2. Read user from file\n" +
                "3. Delete file\n" +
                "4. Show list of files\n" +
                "0. Exit");
    }

    private void showAllFiles() {
        String[] listOfFiles = fileWorker.getListOfFiles(directory);
        if (listOfFiles.length < 1) {
            System.out.println("Directory is empty\n");
            return;
        }
        for (String filename : listOfFiles) {
            System.out.println(filename);
        }
    }

    private void pathInitialisation() {
        System.out.println("Enter directory: ");
        while (true) {
            directory = scanner.nextLine();
            if (new File(directory).exists()) {
                break;
            }
            System.out.println("Wrong path. Try again.");
        }
        char lastChar = directory.charAt(directory.length() - 1);
        if (lastChar != '\\') {
            directory += '\\';
        }
    }

    private int inputInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("You must enter integer number. Try again");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}