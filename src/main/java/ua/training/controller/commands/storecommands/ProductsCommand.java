package ua.training.controller.commands.storecommands;

import ua.training.controller.commands.Command;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.ProductInCheckStoreService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ProductInCheckStoreService service = ServiceFactory.getInstance().getProductInCheckService();
        List<ProductInCheckStore> products = service.findAll();
        session.setAttribute("productsInStore", products);
        return null;
    }


}
