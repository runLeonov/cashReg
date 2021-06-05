package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface IProductInCheckDAO extends ItemDAOI<Integer, ProductInCheckStore> {
    /**
     * Get product from store
     *
     * @param name product name
     * @return product from store
     */
    ProductInCheckStore findByNameOfProd(String name);

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

    /**
     * Get all products from store
     *
     * @return list of products from store
     */
    List<ProductInCheckStore> findAll();

    /**
     * check if there is enough product in store
     *
     * @param weight product weight
     * @param id     of product
     * @return is enough product in store
     */
    boolean isEnough(Double weight, Integer id);

    /**
     * reduce the weight of the product in store
     *
     * @param weight new product weight
     * @param id     id of product
     * @return success of execution
     */
    boolean decrementWeight(Double weight, Integer id);
}
