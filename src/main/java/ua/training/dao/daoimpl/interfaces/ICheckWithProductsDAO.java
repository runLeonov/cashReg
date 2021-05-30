package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;


public interface ICheckWithProductsDAO extends ItemDAOI<Integer, Check> {
    Double getTotalSum();

    boolean insert(Integer id, ProductInCheckStore product);

    Check findById(Integer id);
}
