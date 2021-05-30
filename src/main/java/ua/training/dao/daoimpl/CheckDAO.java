package ua.training.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;
import ua.training.dao.daoimpl.constans.ConstantsDAO;
import ua.training.dao.daoimpl.interfaces.ICheckDAO;
import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDAO implements ICheckDAO {
    private static Logger logger = Logger.getLogger(CheckDAO.class);

    @Override
    public boolean insert() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement =
                     connection.prepareStatement(ConstantsDAO.INSERT_INTO_CHECK)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public Integer getCountOfDeletedChecks() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_COUNT_CANCEL_CHECKS)) {
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Integer getCountOfChecks() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_COUNT_CHECKS)) {
            ResultSet set = statement.executeQuery();
            if (set.first()) {
                return set.getInt(1);
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Check findById(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_CHECKID_BY_ID)) {
            statement.setInt(1, id);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Integer findLast() {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_LAST_CHECK_ID)) {
            ResultSet set = statement.executeQuery();
            if (set.first()) {
                return set.getInt("Id");
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return 0;
    }


    @Override
    public boolean insert(Check entity) {
        return false;
    }

    @Override
    public boolean insertToDeleted(Check entity) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.INSERT_TO_DELETED_CHECKS)) {
            for (ProductInCheckStore p : entity.getProducts()) {
                statement.setInt(1, entity.getId());
                statement.setString(2, p.getProduct().getNameOfProd());
                statement.setDouble(3, p.getWeightOrCount());
                statement.setDouble(4, p.getTotalPrice());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }


    @Override
    public Check update(Check entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.DELETE_CHECKID_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return false;
    }
}
