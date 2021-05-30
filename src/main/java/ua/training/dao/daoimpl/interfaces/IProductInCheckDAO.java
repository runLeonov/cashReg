package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface IProductInCheckDAO extends ItemDAOI<Integer, ProductInCheckStore> {
    ProductInCheckStore findByNameOfProd(String name);

    boolean insertToStore(Product entity, Double weight);

    ProductInCheckStore updateWeight(Double weight, Integer id);

    List<ProductInCheckStore> findAll();

    boolean isEnough(Double weight, Integer id);

    boolean decrementWeight(Double weight, Integer id);
}
