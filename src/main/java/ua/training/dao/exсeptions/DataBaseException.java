package ua.training.dao.exсeptions;

import java.sql.SQLException;

public class DataBaseException extends SQLException {

    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(Exception e) {
        super(e);
    }

    public DataBaseException(String message, Exception e) {
        super(message, e);
    }
}
