package ua.training.service;

import ua.training.dao.daoimpl.CheckDAO;
import ua.training.dao.daoimpl.CheckWithProductsDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.serviceinterfaces.ICheckService;

import java.util.List;

public class CheckService implements ICheckService {
    private CheckWithProductsDAO checkWithProductsDAO = DAOFactory.getInstance().getCheckWithProductsDAO();
    private CheckDAO checkDAO = DAOFactory.getInstance().getCheckDAO();

    @Override
    public Check getById(Integer id) {
        return checkWithProductsDAO.findById(id);
    }

    @Override
    public boolean insert(Check entity) {
        return false;
    }

    public Double getTotalSum() {
        return checkWithProductsDAO.getTotalSum();
    }

    @Override
    public Integer findLast() {
        return checkDAO.findLast();
    }

    @Override
    public boolean insert(List<ProductInCheckStore> productList) {
        boolean f = false;
        checkDAO.insert();
        Integer id = checkDAO.findLast();
        for (ProductInCheckStore prod : productList) {
            f = checkWithProductsDAO.insert(id, prod);
        }
        return f;
    }

    @Override
    public Check update(Check entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (checkWithProductsDAO.findById(id).getProducts().size() == 0) {
            return false;
        }
        checkDAO.insertToDeleted(checkWithProductsDAO.findById(id));
        checkWithProductsDAO.incrementDeletedChecks();
        boolean a = checkWithProductsDAO.delete(id);
        boolean b = checkDAO.delete(id);
        return a && b;
    }
}