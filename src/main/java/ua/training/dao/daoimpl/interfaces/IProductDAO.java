package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Product;

public interface IProductDAO extends ItemDAOI<Integer, Product> {
    /**
     * Get product by name
     *
     * @param name product name
     * @return product from store
     */
    Product findByNameOfProd(String name);

    /**
     * Update product in store
     *
     * @param price new price of product
     * @param id    id of product to update
     * @return updated product from store
     */
    Product updatePrice(Double price, Integer id);
}
