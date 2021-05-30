package ua.training.service.factory;

import ua.training.service.*;

public abstract class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactoryImp();
        }
        return instance;
    }

    public abstract CheckService getCheckService();
    public abstract ProductInCheckStoreService getProductInCheckService();
    public abstract ProductService getProductService();
    public abstract UserService getUserService();
    public abstract ReportGeneratorService getReportGeneratorService();

}
