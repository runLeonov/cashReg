package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.User;

public interface IUserDAO extends ItemDAOI<Integer, User> {
    /**
     * Get user by email and password
     *
     * @param email    of user
     * @param password of user
     * @return user
     */
    User findUser(String email, String password);

    /**
     * Get product by email
     *
     * @param email of user
     * @return user
     */
    User findUserByLogin(String email);
}
