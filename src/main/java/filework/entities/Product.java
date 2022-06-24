package filework.entities;

import java.util.Objects;

public class Product implements Writable {
    private final String name;
    private final String category;
    private final String producer;
    private final int id;

    public Product(String name, String category, String producer, int id) {
        this.name = name;
        this.category = category;
        this.producer = producer;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getId() == product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return name + ',' + category + ',' + producer + ',' + id;
    }

    @Override
    public String getFilename() {
        return "productId" + id;
    }

    public int getId() {
        return id;
    }
}
