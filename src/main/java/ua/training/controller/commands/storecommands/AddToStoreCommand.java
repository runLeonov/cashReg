package ua.training.controller.commands.storecommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.Product;
import ua.training.service.ProductInCheckStoreService;
import ua.training.service.ProductService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class Command to add
 * new product in store
 *
 * @author LeonovOleksand
 */
public class AddToStoreCommand implements Command {
    private static Logger logger = Logger.getLogger(AddToStoreCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ProductInCheckStoreService productInCheckStoreService =
                ServiceFactory.getInstance().getProductInCheckService();
        String nameStr = req.getParameter("newProdName");
        String priceStr = req.getParameter("prodPrice");
        String weightStr = req.getParameter("newWeight");
        try {
            if (!nameStr.equals("")
                    && !priceStr.equals("")
                    && !weightStr.equals("")) {
                Double price = Double.parseDouble(priceStr);
                Double weight = Double.parseDouble(weightStr);
                Product product = new Product.Builder()
                        .withName(nameStr)
                        .withPrice(price)
                        .build();

                ProductService service = ServiceFactory.getInstance().getProductService();
                boolean b = service.insert(product);
                if (!b) {
                    req.setAttribute("prodAlreadyExist", true);
                    return null;
                }
                product = service.findByNameOfProd(nameStr);
                productInCheckStoreService.insertToStore(product, weight);

                logger.info("Product added to store");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("wrongInputAdd", true);
            logger.info("Wrong input!");
            return null;
        }
        return "products";
    }


}
