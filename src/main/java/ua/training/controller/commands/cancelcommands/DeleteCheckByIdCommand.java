package ua.training.controller.commands.cancelcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.service.CheckService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class Command for deleting
 * check from database by id
 *
 * @author LeonovOleksand
 */
public class DeleteCheckByIdCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteCheckByIdCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CheckService service = ServiceFactory.getInstance().getCheckService();
        String idStr = req.getParameter("checkId");
        if (!idStr.equals("")) {
            int id = Integer.parseInt(idStr);
            if (service.delete(id)) {
                logger.info("Check deleted");
                req.setAttribute("checkDeletedById", true);
                req.getSession().setAttribute("checks", null);
                req.getSession().setAttribute("products", null);
            } else {
                logger.info("Check with id: " + id + " not found");
                req.setAttribute("checkNotFoundById", true);
            }
        }
        return null;
    }
}
