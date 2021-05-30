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

public class ShowLastCheckCommand implements Command {
    private static Logger logger = Logger.getLogger(ShowLastCheckCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CheckService service = ServiceFactory.getInstance().getCheckService();
        HttpSession session = req.getSession();
        int id = service.findLast();
        Check check = service.getById(id);
        if (check.getProducts().size() != 0) {
            logger.info("Чек знайдено");
            List<ProductInCheckStore> productInCheckStores = check.getProducts();
            session.setAttribute("products", productInCheckStores);
            session.setAttribute("checks", check);
        } else {
            logger.info("Немає чеків для відображення");
            req.setAttribute("checkNotFoundLast", true);
        }
        return null;
    }
}
