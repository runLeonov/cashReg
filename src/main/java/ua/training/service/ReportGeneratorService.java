package ua.training.service;

import ua.training.dao.daoimpl.CheckDAO;
import ua.training.dao.daoimpl.CheckWithProductsDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Report;

import java.sql.Timestamp;

public class ReportGeneratorService {
    private CheckWithProductsDAO checkWithProductsDAO = DAOFactory.getInstance().getCheckWithProductsDAO();
    private CheckDAO checkDAO = DAOFactory.getInstance().getCheckDAO();

    public Report createAndGetX() {
        Integer countOfChecks = checkDAO.getCountOfChecks();
        Integer deletedChecks = checkDAO.getCountOfDeletedChecks();
        Double totalA = checkWithProductsDAO.getTotalSum();
        Double totalB = 0.0;
        Double totalC = 0.0;
        Double totalSum = totalA + totalB + totalC;
        Double ndsA = totalSum * 0.2;
        Double ndsB = 0.0;
        Double ndsC = 0.0;
        Double totalNds = ndsA + ndsB + ndsC;
        return new Report(
                new Timestamp(System.currentTimeMillis()),
                countOfChecks, deletedChecks,
                totalA, totalB, totalC, totalSum,
                ndsA, ndsB, ndsC, totalNds
        );
    }

    public Report createAndGetZ() {
        return null;
    }

}
