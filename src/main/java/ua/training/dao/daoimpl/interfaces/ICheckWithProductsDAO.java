package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;


public interface ICheckWithProductsDAO extends ItemDAOI<Integer, Check> {


    /**
     * Get get total sum
     *
     * @return the total sum of one prod from check
     */
    Double getTotalSum();

    /**
     * Add new product to check
     *
     * @param id
     * @param product
     * @return success of execution
     */
    boolean insert(Integer id, ProductInCheckStore product);

    /**
     * Get check by id
     *
     * @param id of check
     * @return check object
     */
    Check findById(Integer id);
}
