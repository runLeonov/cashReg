package ua.training.controller.commands.checkcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.CheckService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * class Command to create
 * check with products
 *
 * @author LeonovOleksand
 */
public class CreateCheckCommand implements Command {
    private static final Logger logger = Logger.getLogger(ClearCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CheckService service = ServiceFactory.getInstance().getCheckService();
        HttpSession httpSession = req.getSession();
        @SuppressWarnings("unchecked")
        List<ProductInCheckStore> productInCheckStores =
                (List<ProductInCheckStore>) httpSession.getAttribute("productsInCheck");

        if (Objects.nonNull(productInCheckStores) && !productInCheckStores.isEmpty()) {
            if (service.insert(productInCheckStores)) {
                logger.info("Check created");
                req.setAttribute("checkCreated", true);
            }
            httpSession.setAttribute("productsInCheck", null);
        }

        return null;
    }
}
