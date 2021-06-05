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
import java.util.Objects;

public class AddToCheckCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddToCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        ProductService productService = ServiceFactory.getInstance().getProductService();
        ProductInCheckStoreService storeService = ServiceFactory.getInstance().getProductInCheckService();
        @SuppressWarnings("unchecked")
        List<ProductInCheckStore> productInCheckStores =
                (List<ProductInCheckStore>) httpSession.getAttribute("productsInCheck");
        if (Objects.isNull(productInCheckStores)) {
            productInCheckStores = new ArrayList<>();
            httpSession.setAttribute("productsInCheck", productInCheckStores);
        }

        String prodIdStr = req.getParameter("prodId");
        String weightStr = req.getParameter("weight");
        try {
            Integer prodId = 1;
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
            if (Objects.nonNull(product)) {
                productInCheckStores.add(
                        new ProductInCheckStore.Builder()
                        .withId(product.getId())
                        .withProduct(product)
                        .withWeightOrCount(weight)
                        .withTotalPrice(weight * product.getPrice()).build()
                );
                Double sum = productInCheckStores.stream().mapToDouble(ProductInCheckStore::getTotalPrice).sum();
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
