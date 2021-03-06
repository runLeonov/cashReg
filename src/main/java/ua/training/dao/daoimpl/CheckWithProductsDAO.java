package ua.training.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;
import ua.training.dao.daoimpl.constans.ConstantsDAO;
import ua.training.dao.daoimpl.interfaces.ICheckWithProductsDAO;
import ua.training.dao.entity.Check;
import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckWithProductsDAO implements ICheckWithProductsDAO {

    private static final Logger logger = Logger.getLogger(CheckWithProductsDAO.class);

    @Override
    public boolean insert(Integer id, ProductInCheckStore product) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_INTO_CHECK_WITH_PRODS_ID_AND_PRODS)) {
            statement.setInt(1, id);
            statement.setInt(2, product.getId());
            statement.setDouble(3, product.getWeightOrCount());
            statement.setDouble(4, product.getTotalPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean insert(Integer id, List<ProductInCheckStore> products) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_INTO_CHECK_WITH_PRODS_ID_AND_PRODS)) {
            for (ProductInCheckStore p : products) {
                statement.setInt(1, id);
                statement.setInt(2, p.getId());
                statement.setDouble(3, p.getWeightOrCount());
                statement.setDouble(4, p.getTotalPrice());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean incrementDeletedChecks() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_INTO_CANCEL_CHECKS_EMPTY)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.DELETE_CHECK_WITH_PRODS_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public Double getTotalSum() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_SUM_CHECK_WITH_PRODS)) {
            ResultSet set = statement.executeQuery();
            set.next();
            double sum = set.getDouble(1);
            return sum;
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Check findById(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_CHECK_WITH_PRODS)) {
            List<ProductInCheckStore> productInCheckStores = new ArrayList<>();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameProd = resultSet.getString("p.NameOfProd");
                double price = Double.parseDouble(resultSet.getString("mycheck_and_prods.Price"));
                double weight = Double.parseDouble(resultSet.getString("mycheck_and_prods.Weight"));
                int idOfProd = Integer.parseInt(resultSet.getString("pis.Id"));
                double priceInCheck = Double.parseDouble(resultSet.getString("mycheck_and_prods.Price"));
                productInCheckStores.add(
                        new ProductInCheckStore.Builder()
                                .withId(idOfProd)
                                .withProduct(new Product.Builder()
                                        .withName(nameProd)
                                        .withPrice(price)
                                        .build()
                                )
                                .withWeightOrCount(weight)
                                .withTotalPrice(priceInCheck)
                                .build()
                );
            }
            return new Check.Builder()
                    .withId(id)
                    .withProducts(productInCheckStores)
                    .build();
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean insert(Check entity) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_INTO_CHECK_WITH_PRODS)) {
            for (ProductInCheckStore p : entity.getProducts()) {
                statement.setInt(1, entity.getId());
                statement.setInt(2, p.getId());
                statement.setDouble(3, p.getWeightOrCount());
                statement.setDouble(4, p.getTotalPrice());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public Check update(Check entity) {
        return null;
    }
}
