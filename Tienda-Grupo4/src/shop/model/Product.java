package shop.model;

import java.util.Objects;

public class Product {
    private long idProduct;
    private String name;
    private double price;

    public Product(long idProduct, String name, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
    }

    public Product(String name) {
        this.name = name;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product producto = (Product) o;
        return idProduct == producto.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idProduct);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
