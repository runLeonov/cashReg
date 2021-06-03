package ua.training.service;

import ua.training.dao.daoimpl.UserDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.User;
import ua.training.dao.entity.UserRole;
import ua.training.service.serviceinterfaces.IUserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService implements IUserService {
    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public User getById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public boolean insert(User entity) {
        return userDAO.insert(entity);
    }

    @Override
    public User update(User entity) {
        return userDAO.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return userDAO.delete(id);
    }

    @Override
    public User findUser(String email, String password) {
        return userDAO.findUser(email, password);
    }

    @Override
    public User findUserByLogin(String email) {
        return userDAO.findUserByLogin(email);
    }

    @Override
    public User login(String email, String password) {
        return userDAO.findUser(email, password);

    }

    @Override
    public String returnUserRights(User user) {
        UserRole type = user.getRole();
        if (UserRole.COMMODITY_EXPERT.equals(type)) {
            return "products";
        } else if (UserRole.SENIOR_CASHIER.equals(type)) {
            return "cancel";
        } else if (UserRole.CASHIER.equals(type)) {
            return "check";
        } else {
            return null;
        }
    }

    @Override
    public User registration(String userName, String login, String password) {
        if (userDAO.findUserByLogin(login) != null) {
            return null;
        }
        User user = new User(
                userName,
                password,
                login,
                UserRole.CASHIER,
                1);
        userDAO.insert(user);
        return user;
    }
}
