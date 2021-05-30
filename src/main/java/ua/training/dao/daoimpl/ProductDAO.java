package ua.training.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;
import ua.training.dao.daoimpl.constans.ConstantsDAO;
import ua.training.dao.daoimpl.interfaces.IProductDAO;
import ua.training.dao.entity.Product;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements IProductDAO {
    private static Logger logger = Logger.getLogger(ProductDAO.class);

    @Override
    public Product findByNameOfProd(String name) {
        if (name != null) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.SELECT_ALL_PRODS_BY_NAME)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("products.Id"));
                    product.setPrice(resultSet.getDouble("products.Price"));
                    product.setNameOfProd(resultSet.getString("products.NameOfProd"));
                    return product;
                }

            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public Product findById(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_PRODS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                Product product = new Product();
                product.setId(resultSet.getInt("products.Id"));
                product.setPrice(resultSet.getDouble("products.Price"));
                product.setNameOfProd(resultSet.getString("products.NameOfProd"));
                return product;
            }

        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean insert(Product product) {
        if (product != null) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.INSERT_INTO_PRODS)) {
                statement.setString(1, product.getNameOfProd());
                statement.setString(2, String.valueOf(product.getPrice()));
                statement.executeUpdate();
                return true;
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public Product update(Product entity) {
        if (entity != null) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.UPDATE_PRODS_NEW_NAME_AND_PRICE)) {
                statement.setString(1, entity.getNameOfProd());
                statement.setDouble(2, entity.getPrice());
                statement.setInt(3, entity.getId());
                statement.executeUpdate();
                return entity;
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public Product updatePrice(Double price, Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.UPDATE_PRODS_NEW_PRICE)) {
            statement.setDouble(1, price);
            statement.setDouble(2, id);
            statement.executeUpdate();
            return findById(id);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.DELETE_PROD_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }
}