package ua.training.service;

import ua.training.dao.daoimpl.ProductInCheckStoreDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.serviceinterfaces.IProductInCheckService;

import java.util.List;

public class ProductInCheckStoreService implements IProductInCheckService {
    private ProductInCheckStoreDAO productDAO = DAOFactory.getInstance().getProductInCheckDAO();

    @Override
    public ProductInCheckStore findByNameOfProd(String name) {
        return productDAO.findByNameOfProd(name);
    }

    @Override
    public List<ProductInCheckStore> findAll() {
        return productDAO.findAll();
    }

    @Override
    public ProductInCheckStore getById(Integer id) {
        return productDAO.findById(id);
    }

    public boolean isEnough(Double weight, Integer id) {
        return productDAO.isEnough(weight, id);
    }
    public boolean decrementWeight(Double weight, Integer id) {
        return productDAO.decrementWeight(weight, id);
    }


    @Override
    public boolean insert(ProductInCheckStore entity) {
        return productDAO.insert(entity);
    }

    @Override
    public ProductInCheckStore update(ProductInCheckStore entity) {
        return productDAO.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return productDAO.delete(id);
    }

    @Override
    public boolean insertToStore(Product entity, Double weight) {
        return productDAO.insertToStore(entity, weight);
    }

    @Override
    public ProductInCheckStore updateWeight(Double weight, Integer id) {
        return productDAO.updateWeight(weight, id);
    }
}