package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.User;

public interface IUserService extends ItemService<Integer, User> {
    User findUser(String email, String password);

    User findUserByLogin(String email);

    String returnUserRights(User user);

    User login(String email, String password);

    User registration(String userName, String login, String password);
}
