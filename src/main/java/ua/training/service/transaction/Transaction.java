package ua.training.service.transaction;

import java.sql.Connection;

@FunctionalInterface
public interface Transaction {
    void execute(Connection connection);
}
