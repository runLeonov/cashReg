package ua.training.controller.commands.authorizationcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.User;
import ua.training.service.UserService;
import ua.training.service.factory.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * class Command for
 * registration new user
 *
 * @author LeonovOleksand
 */
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
            logger.info("New user was registered: " + req.getParameter("name"));
            return "check";
        } else {
            logger.info("User with same email already exist");
            req.setAttribute("existsLogin", req.getParameter("email"));
            return null;
        }
    }
}
