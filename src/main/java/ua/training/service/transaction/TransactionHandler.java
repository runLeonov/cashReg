package ua.training.service.transaction;

import org.apache.log4j.Logger;
import ua.training.dao.ConnectorToDB;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler {
    private static Logger logger = Logger.getLogger(TransactionHandler.class);

    public static void execute(Transaction transaction) {
        Connection conn = null;
        try {
            conn = ConnectorToDB.getInstance().connect();
            conn.setAutoCommit(false);
            transaction.execute(conn);
            conn.commit();
        } catch (SQLException e) {
            logger.error("transaction error. ", e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                logger.error("transaction error. ", ex);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                logger.error("transaction error. ", e);
            }
        }
    }
}
