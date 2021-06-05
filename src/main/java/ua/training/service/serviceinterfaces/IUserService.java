package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.User;

public interface IUserService extends ItemService<Integer, User> {
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
    /**
     * Get permissions of user
     *
     * @param user entity
     * @return user's rights
     */
    String returnUserRights(User user);


    /**
     * Get user by email and password
     *
     * @param email    of user
     * @param password of user
     * @return user
     */
    User login(String email, String password);

    /**
     * Add new user to database
     *
     * @param userName of new user
     * @param login    of new uwer
     * @param password of new user
     * @return new user
     */
    User registration(String userName, String login, String password);
}
