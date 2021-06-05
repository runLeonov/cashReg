package ua.training.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;
import ua.training.dao.daoimpl.constans.ConstantsDAO;
import ua.training.dao.daoimpl.interfaces.IUserDAO;
import ua.training.dao.entity.User;

import javax.naming.NamingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class UserDAO implements IUserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);

    @Override
    public User findById(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_USERS)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setIdRole(resultSet.getInt("UserRoleId"));
                user.setRoleById(resultSet.getInt("UserRoleId"));
                resultSet.getInt("Id");
                return user;
            }

        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean insert(User user) {
        if (Objects.nonNull(user)) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.INSERT_INTO_USERS)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
                return true;
            } catch (SQLException | NamingException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public User update(User entity) {
        if (Objects.nonNull(entity)) {
            try (Connection connection = ConnectorToDB.getInstance().connect();
                 PreparedStatement statement = connection.prepareStatement(
                         ConstantsDAO.UPDATE_USERS_BY_ID)) {
                statement.setString(1, entity.getName());
                statement.setString(2, entity.getEmail());
                statement.setString(3, entity.getPassword());
                statement.setInt(4, entity.getRole().getRoleId());
                statement.setInt(5, entity.getId());
                statement.executeUpdate();
                return entity;
            } catch (SQLException | NamingException e) {
                logger.error(e);

            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.DELETE_USERS_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUser(String email, String password) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_USERS_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setIdRole(resultSet.getInt("UserRoleId"));
                user.setRoleById(resultSet.getInt("UserRoleId"));
                return user;
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public User findUserByLogin(String email) {
        try (Connection connection = ConnectorToDB.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantsDAO.SELECT_ALL_USERS_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setIdRole(resultSet.getInt("UserRoleId"));
                user.setRoleById(resultSet.getInt("UserRoleId"));
                return user;
            }
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        return null;
    }
}