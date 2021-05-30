package ua.training.controller.commands.checkcommands;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.dao.entity.Product;
import ua.training.dao.entity.ProductInCheckStore;
import ua.training.service.ProductInCheckStoreService;
import ua.training.service.ProductService;
import ua.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddToCheckCommand implements Command {
    private static Logger logger = Logger.getLogger(AddToCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        ProductService productService = ServiceFactory.getInstance().getProductService();
        ProductInCheckStoreService storeService = ServiceFactory.getInstance().getProductInCheckService();
        @SuppressWarnings("unchecked")
        List<ProductInCheckStore> productInCheckStores =
                (List<ProductInCheckStore>) httpSession.getAttribute("productsInCheck");
        if (productInCheckStores == null) {
            productInCheckStores = new ArrayList<>();
            httpSession.setAttribute("productsInCheck", productInCheckStores);
        }

        String prodIdStr = req.getParameter("prodId");
        String weightStr = req.getParameter("weight");
        try {
            Integer prodId = null;
            if (prodIdStr != "") {
                prodId = Integer.parseInt(prodIdStr);
            }
            String prodName = String.valueOf(req.getParameter("prodName"));
            Double weight = 1.0;
            if (weightStr != "") {
                weight = Double.parseDouble(weightStr);
            }
            Product product;
            if (prodName != "") {
                product = productService.findByNameOfProd(prodName);
            } else {
                product = productService.getById(prodId);
            }
            if (storeService.isEnough(weight, product.getId())) {
                storeService.decrementWeight(weight, product.getId());
            } else {
                req.setAttribute("notEnough", true);
                return null;
            }
            if (product != null) {
                productInCheckStores.add(new ProductInCheckStore(product.getId(), product, weight, weight * product.getPrice()));
                Double sum = productInCheckStores.stream().mapToDouble(x -> x.getTotalPrice()).sum();
                httpSession.setAttribute("sumOfProds", sum);
            } else {
                req.setAttribute("notFound", true);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("wrongInput", true);
        }
        return null;
    }
}
