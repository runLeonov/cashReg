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
        return createRep();
    }

    public Report createAndGetZ() {
        return createRep();
    }

    private Report createRep() {
        int countOfChecks = checkDAO.getCountOfChecks();
        int deletedChecks = checkDAO.getCountOfDeletedChecks();
        double totalA = checkWithProductsDAO.getTotalSum();
        double totalB = 0.0;
        double totalC = 0.0;
        double totalSum = totalA + totalB + totalC;
        double ndsA = totalSum * 0.2;
        double ndsB = 0.0;
        double ndsC = 0.0;
        double totalNds = ndsA + ndsB + ndsC;
        return new Report.Builder()
                .withPrintTime(new Timestamp(System.currentTimeMillis()))
                .withCountCancelChecks(deletedChecks)
                .withCountChecks(countOfChecks)
                .withTotalA(totalA)
                .withTotalB(totalB)
                .withTotalC(totalC)
                .withSumTotal(totalSum)
                .withNdsTotalA(ndsA)
                .withNdsTotalB(ndsB)
                .withNdsTotalC(ndsC)
                .withSumNdsTotal(totalNds)
                .build();
    }

}
