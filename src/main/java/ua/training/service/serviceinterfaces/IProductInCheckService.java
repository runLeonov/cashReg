package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface IProductInCheckService extends ItemService<Integer, ProductInCheckStore> {
    /**
     * Get product from store
     *
     * @param name product name
     * @return product from store
     */
    ProductInCheckStore findByNameOfProd(String name);

    /**
     * Get all products from store
     *
     * @return list of products from store
     */
    List<ProductInCheckStore> findAll();

    /**
     * Add new product to store
     *
     * @param entity new product
     * @param weight of new product
     * @return success of execution
     */
    boolean insertToStore(Product entity, Double weight);

    /**
     * Update product in store
     *
     * @param weight product name
     * @param id     of product to update
     * @return updated product from store
     */
    ProductInCheckStore updateWeight(Double weight, Integer id);
}
