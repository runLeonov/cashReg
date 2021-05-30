package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.User;

public interface IUserDAO extends ItemDAOI<Integer, User> {
    User findUser(String email, String password);

    User findUserByLogin(String email);
}
