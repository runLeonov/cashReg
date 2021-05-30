package ua.training.controller.commands.authorizationcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.User;
import ua.training.service.UserService;
import ua.training.service.factory.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = ServiceFactory.getInstance().getUserService();

        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        User user = service.registration(userName, email, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            logger.info("Зареєстрований новий користувач: " + req.getParameter("name"));
            return "login";
        } else {
            logger.info("Користувач під таким емейлом вже існує");
            req.setAttribute("existsLogin", req.getParameter("email"));
            return null;
        }
    }
}
