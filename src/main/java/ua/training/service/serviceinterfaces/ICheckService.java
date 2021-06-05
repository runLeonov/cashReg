package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface ICheckService extends ItemService<Integer, Check> {

    /**
     * Get id of last check
     *
     * @return the id of the last check
     */
    Integer findLast();

    /**
     * Add products to check
     *
     * @param productList products in check
     * @return success of execution
     */
    boolean insert(List<ProductInCheckStore> productList);
}
