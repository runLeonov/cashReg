package ua.training.dao.entity;

public class ProductInCheckStore extends Model {

    private Product product;

    private double weightOrCount;

    private double totalPrice;

    public ProductInCheckStore(Product product) {
        this.product = product;
    }

    public ProductInCheckStore(int id, Product product, double weightOrCount, double totalPrice) {
        super(id);
        this.product = product;
        this.weightOrCount = weightOrCount;
        this.totalPrice = totalPrice;
    }

    public ProductInCheckStore(Product product, double weightOrCount, double totalPrice) {
        this.product = product;
        this.weightOrCount = weightOrCount;
        this.totalPrice = totalPrice;
    }

    public ProductInCheckStore(Integer idOfProd, Product product, Double weight) {
        super(idOfProd);
        this.product = product;
        this.weightOrCount = weight;
    }

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
    public String toString() {
        return "ProductInCheck{" +
                "prodId=" + super.getId() +
                ", product=" + product +
                ", weightOrCount=" + weightOrCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
