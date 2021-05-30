package ua.training.dao.daoimpl.factory;

import org.apache.log4j.Logger;
import ua.training.dao.daoimpl.*;

public class DatabaseDAOFactory extends DAOFactory {
    private static Logger logger = Logger.getLogger(DatabaseDAOFactory.class);

    CheckDAO checkDAO = new CheckDAO();
    ProductDAO productDAO = new ProductDAO();
    ProductInCheckStoreDAO productInCheckStoreDAO = new ProductInCheckStoreDAO();
    UserDAO userDAO = new UserDAO();
    CheckWithProductsDAO checkWithProductsDAO = new CheckWithProductsDAO();

    @Override
    public CheckDAO getCheckDAO() {
        logger.info("Get CheckDAO");
        return checkDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        logger.info("Get ProductDAO");
        return productDAO;
    }

    @Override
    public ProductInCheckStoreDAO getProductInCheckDAO() {
        logger.info("Get ProductInCheckDAO");
        return productInCheckStoreDAO;
    }

    @Override
    public UserDAO getUserDAO() {
        logger.info("Get UserDAO");
        return userDAO;
    }

    @Override
    public CheckWithProductsDAO getCheckWithProductsDAO() {
        logger.info("Get CheckWithProductsDAO");
        return checkWithProductsDAO;
    }


}
