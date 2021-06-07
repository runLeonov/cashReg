package ua.training.service;

import org.apache.log4j.Logger;
import ua.training.dao.daoimpl.CheckDAO;
import ua.training.dao.daoimpl.CheckWithProductsDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.serviceinterfaces.ICheckService;
import ua.training.service.transaction.TransactionHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        checkDAO.insert();
        return checkWithProductsDAO.insert(checkDAO.findLast(), productList);
    }

    @Override
    public Check update(Check entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        AtomicBoolean f = new AtomicBoolean(false);
        TransactionHandler.execute(connection -> {
            if (checkWithProductsDAO.findById(id).getProducts().size() == 0) {
                f.set(false);
                return;
            }
            checkDAO.insertToDeleted(checkWithProductsDAO.findById(id));
            checkWithProductsDAO.incrementDeletedChecks();
            f.set(checkWithProductsDAO.delete(id) && checkDAO.delete(id));
        });
        return f.get();
    }
}
