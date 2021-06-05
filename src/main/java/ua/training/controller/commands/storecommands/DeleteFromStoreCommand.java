package ua.training.controller.commands.storecommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.service.ProductInCheckStoreService;
import ua.training.service.ProductService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class DeleteFromStoreCommand implements Command {
    private static Logger logger = Logger.getLogger(DeleteFromStoreCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ProductInCheckStoreService service = ServiceFactory.getInstance().getProductInCheckService();
        ProductService productService = ServiceFactory.getInstance().getProductService();
        String idStr = req.getParameter("deleteId");

        try {
            if (!idStr.equals("")) {
                Integer id = Integer.parseInt(idStr);
                if (Objects.isNull(service.getById(id)) || Objects.isNull(productService.getById(id))) {
                    logger.info("Продукт не знайдено: " + id);
                    req.setAttribute("notFoundDelete", true);
                    return null;
                }
                service.delete(id);
                productService.delete(id);
                logger.info("Продукт успішно видалено: " + id);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("wrongInputDelete", true);
            return null;
        }
        req.setAttribute("deleteId", null);
        return "products";
    }
}
