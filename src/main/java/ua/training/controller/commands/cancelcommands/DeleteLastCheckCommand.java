package ua.training.controller.commands.cancelcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.service.CheckService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * class Command for deleting
 * last check from database
 *
 * @author LeonovOleksand
 */
public class DeleteLastCheckCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteLastCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CheckService service = ServiceFactory.getInstance().getCheckService();
        int id = service.findLast();
        if (service.delete(id)) {
            logger.info("Check deleted");
            req.setAttribute("checkDeletedLast", true);
        } else {
            logger.info("No checks in database");
            req.setAttribute("checkNotFoundLast", true);
        }
        req.getSession().setAttribute("checks", null);
        req.getSession().setAttribute("products", null);
        return null;
    }
}
