package ua.training.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.training.dao.exсeptions.DataBaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class ConnectorToDB {

    private static ConnectorToDB pool;
    private final DataSource dataSource;

    public ConnectorToDB() throws DataBaseException {
        dataSource = getMysqlDataSource();
    }

    private static DataSource getMysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("cashReg1?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public static synchronized ConnectorToDB getInstance() throws DataBaseException {
        if (pool == null) {
            pool = new ConnectorToDB();
        }
        return pool;
    }

    public Connection connect() throws SQLException {
        return dataSource.getConnection();
    }

}
