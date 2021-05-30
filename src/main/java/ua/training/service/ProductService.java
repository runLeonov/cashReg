package ua.training.service;

import ua.training.dao.daoimpl.ProductDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Product;
import ua.training.service.serviceinterfaces.IProductService;

public class ProductService implements IProductService {
    private ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();

    @Override
    public Product findByNameOfProd(String name) {
        return productDAO.findByNameOfProd(name);
    }

    @Override
    public Product getById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean insert(Product entity) {
        return productDAO.insert(entity);
    }

    @Override
    public Product update(Product entity) {
        return productDAO.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return productDAO.delete(id);
    }

    @Override
    public Product updatePrice(Double price, Integer id) {
        return productDAO.updatePrice(price, id);
    }
}
