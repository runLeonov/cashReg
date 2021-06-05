package ua.training.controller.commands.cancelcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.CheckService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * class Command for showing
 * check from database by id
 *
 * @author LeonovOleksand
 */
public class ShowCheckByIdCommand implements Command {
    private static final Logger logger = Logger.getLogger(ShowCheckByIdCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        CheckService service = ServiceFactory.getInstance().getCheckService();
        String idStr = req.getParameter("checkId");
        if (!idStr.equals("")) {
            int id = Integer.parseInt(idStr);
            Check check = service.getById(id);
            if (check.getProducts().size() != 0) {
                logger.info("Check founded");
                List<ProductInCheckStore> productInCheckStores = check.getProducts();
                session.setAttribute("products", productInCheckStores);
                session.setAttribute("checks", check);
            } else {
                logger.info("Check with id: " + id + " not found");
                req.setAttribute("checkNotFoundById", true);
            }
        }
        return null;
    }
}
