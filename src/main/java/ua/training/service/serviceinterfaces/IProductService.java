package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Product;

public interface IProductService extends ItemService<Integer, Product> {
    Product findByNameOfProd(String name);

    Product updatePrice(Double price, Integer id);
}
