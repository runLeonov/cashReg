package ua.training.dao.entity;

import java.io.Serializable;

/**
 * @author LeonovOleksand
 */
public class Product extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nameOfProd;
    private double price;

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

    public static class Builder {
        private Product product;

        public Builder() {
            product = new Product();
        }

        public Builder withName(String name) {
            product.nameOfProd = name;
            return this;
        }

        public Builder withId(int id) {
            product.setId(id);
            return this;
        }

        public Builder withPrice(double price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }

    }
}
