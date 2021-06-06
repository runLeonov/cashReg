package ua.training.dao.entity;

import java.io.Serializable;

/**
 * @author LeonovOleksand
 */
public class ProductInCheckStore extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private Product product;
    private double weightOrCount;
    private double totalPrice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductInCheckStore() {
    }

    public double getWeightOrCount() {
        return weightOrCount;
    }

    public void setWeightOrCount(double weightOrCount) {
        this.weightOrCount = weightOrCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInCheckStore that = (ProductInCheckStore) o;

        if (Double.compare(that.weightOrCount, weightOrCount) != 0) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product != null ? product.hashCode() : 0;
        temp = Double.doubleToLongBits(weightOrCount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ProductInCheck{" +
                "prodId=" + super.getId() +
                ", product=" + product +
                ", weightOrCount=" + weightOrCount +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private ProductInCheckStore productInCheckStore;

        public Builder() {
            productInCheckStore = new ProductInCheckStore();
        }

        public Builder withId(int id) {
            productInCheckStore.setId(id);
            return this;
        }

        public Builder withProduct(Product product) {
            productInCheckStore.product = product;
            return this;
        }

        public Builder withWeightOrCount(double weightOrCount) {
            productInCheckStore.weightOrCount = weightOrCount;
            return this;
        }

        public Builder withTotalPrice(double totalPrice) {
            productInCheckStore.totalPrice = totalPrice;
            return this;
        }

        public ProductInCheckStore build() {
            return productInCheckStore;
        }
    }

}
