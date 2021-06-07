package ua.training.service;

import ua.training.dao.daoimpl.UserDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.User;
import ua.training.dao.entity.UserRole;
import ua.training.service.serviceinterfaces.IUserService;
import ua.training.service.transaction.TransactionHandler;

import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
        } else {
            return "check";
        }
    }

    @Override
    public User registration(String userName, String login, String password) {
        AtomicReference<User> user = new AtomicReference<>(new User());
        TransactionHandler.execute(connection -> {
            if (Objects.nonNull(userDAO.findUserByLogin(login))) {
                return;
            }
            user.set(new User.Builder()
                    .withName(userName)
                    .withPassword(password)
                    .withEmail(login)
                    .withUserRole(UserRole.CASHIER)
                    .withUserRoleId(1)
                    .build());
            userDAO.insert(user.get());
        });
        return user.get();
    }
}
