package filework;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose objects to work:" +
                "\n1. Users" +
                "\n2. Products");
        switch (scanner.nextLine()) {
            case "1":
                new UserFileStorageConsoleApp().run();
                break;
            case "2":
                new ProductFileStorageConsoleApp().run();
                break;
            default:
                System.out.println("Wrong enter");

        }
    }
}
