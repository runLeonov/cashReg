package ua.training.controller.commands.authorizationcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.User;
import ua.training.service.UserService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Command for authorization
 *
 * @author LeonovOleksand
 */
public class LoginCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = ServiceFactory.getInstance().getUserService();
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User dbUser = service.findUser(email, password);

        if (Objects.nonNull(dbUser)) {
            session.setAttribute("userNotExists", null);
            session.setAttribute("user", dbUser);
            logger.info("User: " + dbUser.getName() + "was authorized");
            return service.returnUserRights(dbUser);
        } else {
            req.setAttribute("userNotExists", true);
            logger.info("User wasn't found");
        }
        return "login";
    }


}
