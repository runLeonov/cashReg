package ua.training.controller.commands.checkcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearCheckCommand implements Command {
    private static Logger logger = Logger.getLogger(ClearCheckCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("productsInCheck") != null) {
            logger.info("Чек очищено");
            httpSession.setAttribute("productsInCheck", null);
        }

        return "check";
    }
}
