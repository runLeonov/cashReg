package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface IProductInCheckService extends ItemService<Integer, ProductInCheckStore> {
    ProductInCheckStore findByNameOfProd(String name);

    List<ProductInCheckStore> findAll();

    boolean insertToStore(Product entity, Double weight);

    ProductInCheckStore updateWeight(Double weight, Integer id);
}
