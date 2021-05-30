package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Product;

public interface IProductDAO extends ItemDAOI<Integer, Product> {
    Product findByNameOfProd(String name);

    Product updatePrice(Double price, Integer id);
}
