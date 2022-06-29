package filework;

import filework.entities.Product;
import filework.storage.ProductFileStorage;

public class ProductFileStorageConsoleApp extends FileStorageConsoleApp<Product> {

    public ProductFileStorageConsoleApp() {
        fileStorage = new ProductFileStorage();
        pathInitialisation();
    }

    @Override
    Product initializeEntity() {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();
        System.out.println("Producer: ");
        String producer = scanner.nextLine();
        System.out.println("ID: ");
        int id = inputInt();
        scanner.nextLine();
        return new Product(name, category, producer, id);
    }
}
