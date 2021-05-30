package ua.training.dao.daoimpl.factory;

import ua.training.dao.daoimpl.*;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseDAOFactory();
        }
        return instance;
    }

    public abstract CheckDAO getCheckDAO();
    public abstract ProductDAO getProductDAO();
    public abstract ProductInCheckStoreDAO getProductInCheckDAO();
    public abstract UserDAO getUserDAO();
    public abstract CheckWithProductsDAO getCheckWithProductsDAO();
}
