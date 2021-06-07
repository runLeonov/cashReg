package ua.training.service;

import ua.training.dao.daoimpl.CheckDAO;
import ua.training.dao.daoimpl.CheckWithProductsDAO;
import ua.training.dao.daoimpl.factory.DAOFactory;
import ua.training.dao.entity.Report;
import ua.training.dao.entity.User;
import ua.training.service.transaction.TransactionHandler;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Report> report = new AtomicReference<>(new Report());
        TransactionHandler.execute(connection -> {
            int countOfChecks = checkDAO.getCountOfChecks();
            int deletedChecks = checkDAO.getCountOfDeletedChecks();
            double totalA = checkWithProductsDAO.getTotalSum();
            double totalB = 0.0;
            double totalC = 0.0;
            double totalSum = Math.round((totalA + totalB + totalC) * 100.0) / 100.0;
            double ndsA = totalSum * 0.2;
            double ndsB = 0.0;
            double ndsC = 0.0;
            double totalNds = Math.round((ndsA + ndsB + ndsC) * 100.0) / 100.0;
            report.set(new Report.Builder()
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
                    .build());
        });
        return report.get();
    }

}
