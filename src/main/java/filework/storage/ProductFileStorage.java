package filework.storage;

import filework.entities.Product;

public class ProductFileStorage extends FileStorage<Product> {
    private static final String SUB_DIR = "Product";

    @Override
    Product deserialize(String[] itemArray) {
        return new Product(itemArray[0], itemArray[1], itemArray[2], Integer.parseInt(itemArray[3]));
    }

    @Override
    public String getSubDir() {
        return SUB_DIR;
    }
}
