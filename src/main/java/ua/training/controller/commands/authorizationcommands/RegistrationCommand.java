package ua.training.controller.commands.authorizationcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.User;
import ua.training.service.UserService;
import ua.training.service.factory.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RegistrationCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = service.registration(
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("password")
        );

        if (Objects.nonNull(user) && Objects.nonNull(user.getName())) {
            req.getSession().setAttribute("user", user);
            logger.info("Зареєстрований новий користувач: " + req.getParameter("name"));
            return "login";
        } else {
            logger.info("Користувач під таким емейлом вже існує");
            req.setAttribute("existsLogin", req.getParameter("email"));
            return "registration";
        }
    }
}
