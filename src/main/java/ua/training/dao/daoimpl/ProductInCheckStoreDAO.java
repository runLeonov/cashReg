package ua.training.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;
import ua.training.dao.daoimpl.constans.ConstantsDAO;
import ua.training.dao.daoimpl.interfaces.IProductInCheckDAO;
import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductInCheckStoreDAO implements IProductInCheckDAO {
    private static final Logger logger = Logger.getLogger(ProductInCheckStoreDAO.class);

    @Override
    public ProductInCheckStore findByNameOfProd(String name) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_PRODS_IN_STORE_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                ProductInCheckStore product = new ProductInCheckStore();
                product.setId(resultSet.getInt("products.Id"));
                product.getProduct().setPrice(resultSet.getDouble("products.Price"));
                product.getProduct().setNameOfProd(resultSet.getString("products.NameOfProd"));
                product.setTotalPrice(resultSet.getDouble("prod_in_store.TotalPrice"));
                product.setWeightOrCount(resultSet.getDouble("prod_in_store.Weight"));
                return product;
            }

        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<ProductInCheckStore> findAll() {
        List<ProductInCheckStore> productInCheckStores = new ArrayList<>();
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_PRODS_IN_STORE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameProd = resultSet.getString("products.NameOfProd");
                double price = Double.parseDouble(resultSet.getString("products.Price"));
                double weight = Double.parseDouble(resultSet.getString("prod_in_store.Weight"));
                double total = Double.parseDouble(resultSet.getString("prod_in_store.Weight"));
                int id = Integer.parseInt(resultSet.getString("prod_in_store.Id"));
                productInCheckStores.add(
                        new ProductInCheckStore.Builder()
                                .withId(id)
                                .withProduct(new Product.Builder()
                                        .withName(nameProd)
                                        .withPrice(price)
                                        .build()
                                )
                                .withWeightOrCount(weight)
                                .withTotalPrice(total)
                                .build()
                );
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return productInCheckStores;
    }

    @Override
    public boolean isEnough(Double weight, Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_WEIGHT_PRODS_IN_STORE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                Double weightInStore = resultSet.getDouble(1);
                return weight < weightInStore;
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean decrementWeight(Double weight, Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_WEIGHT_PRODS_IN_STORE_BY_ID);
             PreparedStatement statement2 = connection.prepareStatement(
                     ConstantsDAO.UPDATE_WEIGHT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.first()) {
                Double weightInStore = set.getDouble(1);
                statement2.setDouble(1, weightInStore - weight);
                statement2.setInt(2, id);
                statement2.executeUpdate();
            }
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public ProductInCheckStore findById(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_PRODS_IN_STORE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                Product p = new Product.Builder()
                        .withName(resultSet.getString("products.NameOfProd"))
                        .withPrice(resultSet.getDouble("products.Price"))
                        .build();
                int idEd = resultSet.getInt("products.Id");
                double totalPrice = resultSet.getDouble("prod_in_store.TotalPrice");
                double weight = resultSet.getDouble("prod_in_store.Weight");
                return new ProductInCheckStore.Builder()
                        .withId(idEd)
                        .withProduct(p)
                        .withWeightOrCount(weight)
                        .withTotalPrice(totalPrice)
                        .build();
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean insert(ProductInCheckStore entity) {
        if (entity != null) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.INSERT_INTO_PRODS_IN_STORE)) {
                statement.setDouble(1, entity.getWeightOrCount());
                statement.setInt(2, entity.getProduct().getId());
                statement.executeUpdate();
                return true;
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public boolean insertToStore(Product entity, Double weight) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_INTO_PRODS_IN_STORE_BY_ID)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
            return false;
        }
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement2 = connection.prepareStatement(
                     ConstantsDAO.UPDATE_PRODS_IN_STORE_WEIGHT_BY_ID)) {
            statement2.setDouble(1, weight);
            statement2.setInt(2, entity.getId());
            statement2.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public ProductInCheckStore update(ProductInCheckStore entity) {
        if (Objects.nonNull(entity)) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.UPDATE_PRODS_IN_STORE_WEIGHT_BY_ID)) {
                statement.setDouble(1, entity.getWeightOrCount());
                statement.setInt(2, entity.getId());
                statement.executeUpdate();
                return entity;
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public ProductInCheckStore updateWeight(Double weight, Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.UPDATE_PRODS_IN_STORE_WEIGHT_BY_ID)) {
            statement.setDouble(1, weight);
            statement.setInt(2, id);
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
                     ConstantsDAO.DELETE_PRODS_IN_STORE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return false;
    }


}
