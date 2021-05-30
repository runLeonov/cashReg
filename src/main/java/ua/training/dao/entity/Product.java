package ua.training.dao.entity;

public class Product extends Model {
    private String nameOfProd;
    private double price;

    public Product() {
    }

    public Product(String nameOfProd, double price) {
        this.nameOfProd = nameOfProd;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return nameOfProd != null ? nameOfProd.equals(product.nameOfProd) : product.nameOfProd == null;
    }

    @Override
    public int hashCode() {
        return nameOfProd != null ? nameOfProd.hashCode() : 0;
    }

    public String getNameOfProd() {
        return nameOfProd;
    }

    public void setNameOfProd(String nameOfProd) {
        this.nameOfProd = nameOfProd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + super.getId() +
                "nameOfProd='" + nameOfProd + '\'' +
                ", price=" + price +
                '}';
    }
}
