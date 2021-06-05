package ua.training.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class Check extends Model {
    private List<ProductInCheckStore> products = new ArrayList<>();

    public List<ProductInCheckStore> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCheckStore> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id" + super.getId() +
                ", products=" + products.toString() +
                '}';
    }

    public static class Builder {
        private Check check;

        public Builder() {
            check = new Check();
            check.products = new ArrayList<>();
        }

        public Builder withProducts(List<ProductInCheckStore> products) {
            check.products = products;
            return this;
        }

        public Builder withId(int id) {
            check.setId(id);
            return this;
        }

        public Check build() {
            return check;
        }
    }
}
