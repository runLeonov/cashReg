package ua.training.controller.commands.storecommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.ProductInCheckStoreService;
import ua.training.service.ProductService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductCommand implements Command {
    private static Logger logger = Logger.getLogger(UpdateProductCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ProductService service =
                ServiceFactory.getInstance().getProductService();
        ProductInCheckStoreService checkService =
                ServiceFactory.getInstance().getProductInCheckService();
        String newPriceStr = req.getParameter("newProdPriceEd");
        String idStr = req.getParameter("idEd");
        String newWeightStr = req.getParameter("newWeightEd");
        try {
            if (!newPriceStr.equals("")
                    && !idStr.equals("")
                    && !newWeightStr.equals("")) {
                Double price = Double.parseDouble(newPriceStr);
                Integer id = Integer.parseInt(idStr);
                Double weight = Double.parseDouble(newWeightStr);
                Product product = service.updatePrice(price, id);
                ProductInCheckStore productInCheckStore = checkService.updateWeight(weight, id);
                if (productInCheckStore == null || product == null) {
                    logger.info("Продукт не знайдено: " + id);
                    req.setAttribute("notFoundUpdate", true);
                    return null;
                }
                logger.info("Продукт успішно змінено: " + id);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("wrongInputUpdate", true);
            return null;
        }
        req.setAttribute("newProdPriceEd", null);
        return "products";
    }
}
