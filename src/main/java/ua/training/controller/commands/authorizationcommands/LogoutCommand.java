package ua.training.controller.commands.authorizationcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String lang = (String)session.getAttribute("language");
        session.invalidate();
        if (lang != "" && Objects.nonNull(lang)) {
            req.getSession().setAttribute("language", lang);
        }
        return "login";
    }
}
