package ua.training.dao.daoimpl.constans;

public final class ConstantsDAO {
    public static final String INSERT_INTO_CHECK = "INSERT INTO mycheck VALUES ()";
    public static final String SELECT_COUNT_CANCEL_CHECKS = "SELECT COUNT(Id) FROM cancel_checks";
    public static final String SELECT_COUNT_CHECKS = "SELECT COUNT(Id) FROM mycheck";
    public static final String SELECT_ALL_CHECKID_BY_ID = "SELECT * FROM mycheck WHERE Id = ?";
    public static final String SELECT_LAST_CHECK_ID = "SELECT * FROM mycheck ORDER BY Id DESC LIMIT 1";
    public static final String DELETE_CHECKID_BY_ID = "DELETE FROM mycheck WHERE Id = ?";
    public static final String INSERT_TO_DELETED_CHECKS = "INSERT INTO deleted_check " +
            "(CheckId, NameOfProd, Weight, Price) VALUES (?,?,?,?)";

    public static final String INSERT_INTO_CHECK_WITH_PRODS = "INSERT INTO mycheck_and_prods" +
            " (CheckId, ProductId, Weight, Price) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ALL_CHECK_WITH_PRODS = "SELECT * FROM mycheck_and_prods JOIN" +
            " prod_in_store pis ON pis.Id = mycheck_and_prods.ProductId JOIN" +
            " products p ON p.Id = pis.ProdId WHERE CheckId = ?";
    public static final String SELECT_SUM_CHECK_WITH_PRODS = "SELECT SUM(Price) FROM mycheck_and_prods";
    public static final String DELETE_CHECK_WITH_PRODS_BY_ID = "DELETE FROM mycheck_and_prods WHERE CheckId = ?";
    public static final String INSERT_INTO_CANCEL_CHECKS_EMPTY = "INSERT INTO cancel_checks VALUES()";
    public static final String INSERT_INTO_CHECK_WITH_PRODS_ID_AND_PRODS = "INSERT INTO mycheck_and_prods" +
            " (CheckId, ProductId, Weight, Price) VALUES (?, ?, ?, ?)";


    public static final String SELECT_ALL_PRODS_BY_NAME = "SELECT * FROM products " +
            "WHERE NameOfProd = (?)";
    public static final String SELECT_ALL_PRODS_BY_ID = "SELECT * FROM products WHERE Id = (?)";
    public static final String INSERT_INTO_PRODS = "INSERT INTO products (NameOfProd, Price) VALUES (?, ?)";
    public static final String UPDATE_PRODS_NEW_NAME_AND_PRICE = "UPDATE products SET NameOfProd = ?, Price = ? WHERE Id = ?";
    public static final String UPDATE_PRODS_NEW_PRICE = "UPDATE products SET Price = ? WHERE Id = ?";
    public static final String DELETE_PROD_BY_ID = "DELETE FROM products WHERE id = ?";

    public static final String SELECT_ALL_PRODS_IN_STORE_BY_NAME = "SELECT * FROM prod_in_store JOIN products ON " +
            "prod_in_store.Id = products.Id WHERE products.NameOfProd = ?";
    public static final String SELECT_ALL_PRODS_IN_STORE = "SELECT * FROM prod_in_store JOIN products " +
            "ON prod_in_store.Id = products.Id";
    public static final String SELECT_ALL_PRODS_IN_STORE_BY_ID = "SELECT * FROM prod_in_store JOIN products " +
            "ON prod_in_store.Id = products.Id WHERE products.Id = ?";
    public static final String INSERT_INTO_PRODS_IN_STORE = "INSERT INTO prod_in_store "
            + "(Weight, ProdId) VALUES (?, ?)";
    public static final String INSERT_INTO_PRODS_IN_STORE_BY_ID = "INSERT INTO prod_in_store (ProdId) " +
            "SELECT products.Id FROM products WHERE products.Id = (?)";
    public static final String UPDATE_PRODS_IN_STORE_WEIGHT_BY_ID = "UPDATE prod_in_store SET Weight = ? WHERE Id = ?";
    public static final String DELETE_PRODS_IN_STORE_BY_ID = "DELETE FROM prod_in_store WHERE Id = ?";
    public static final String SELECT_WEIGHT_PRODS_IN_STORE_BY_ID = "SELECT Weight FROM prod_in_store WHERE Id = ?";
    public static final String UPDATE_WEIGHT_BY_ID = "UPDATE prod_in_store SET Weight = ? WHERE Id = ?";

    public static final String SELECT_ALL_USERS = "SELECT * FROM users " + "WHERE Id = ?";
    public static final String INSERT_INTO_USERS = "INSERT INTO users (Name, Password, Email, UserRoleId) " +
            "VALUES (?, ?, ?, 1)";
    public static final String UPDATE_USERS_BY_ID = "UPDATE users SET Name = ?, Email = ?, Password = ?," +
            " UserRoleId = ? WHERE Id = ?";
    public static final String DELETE_USERS_BY_ID = "DELETE FROM users WHERE Id = ?";
    public static final String SELECT_ALL_USERS_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users "
            + "WHERE Email = (?) AND Password = (?)";
    public static final String SELECT_ALL_USERS_BY_EMAIL = "SELECT * FROM users WHERE Email = ?";
}
