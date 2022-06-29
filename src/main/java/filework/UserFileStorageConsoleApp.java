package filework;

import filework.entities.User;
import filework.storage.UserFileStorage;

public class UserFileStorageConsoleApp extends FileStorageConsoleApp<User> {

    public UserFileStorageConsoleApp() {
        fileStorage = new UserFileStorage();
        pathInitialisation();
    }

    @Override
    User initializeEntity() {
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
}
