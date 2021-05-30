package ua.training.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class Check extends Model {
    private List<ProductInCheckStore> products = new ArrayList<>();

    public Check(List<ProductInCheckStore> products) {
        this.products = products;
    }

    public Check(Integer id, List<ProductInCheckStore> products) {
        super(id);
        this.products = products;
    }

    public Check() {
    }

    @Override
    public String toString() {
        return "Check{" +
                "id" + super.getId() +
                ", products=" + products.toString() +
                '}';
    }

    public List<ProductInCheckStore> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCheckStore> products) {
        this.products = products;
    }
}
