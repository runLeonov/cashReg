package ua.training.service.factory;

import org.apache.log4j.Logger;
import ua.training.service.*;

public class ServiceFactoryImp extends ServiceFactory {

    private static Logger logger = Logger.getLogger(ServiceFactoryImp.class);

    private CheckService checkService = new CheckService();
    private ProductInCheckStoreService productInCheckStoreService = new ProductInCheckStoreService();
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();
    private ReportGeneratorService reportGeneratorService = new ReportGeneratorService();
    @Override
    public CheckService getCheckService() {
        logger.info("Get checkService");
        return checkService;
    }

    @Override
    public ProductInCheckStoreService getProductInCheckService() {
        logger.info("Get productInCheckService");
        return productInCheckStoreService;
    }

    @Override
    public ProductService getProductService() {
        logger.info("Get productService");
        return productService;
    }

    @Override
    public UserService getUserService() {
        logger.info("Get userService");
        return userService;
    }

    @Override
    public ReportGeneratorService getReportGeneratorService() {
        logger.info("Get ReportGeneratorService");
        return reportGeneratorService;
    }
}
