package nextstep.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public static void initialize(String _driver, String url, String userName, String password) {
        DRIVER = _driver;
        URL = url;
        USERNAME = userName;
        PASSWORD = password;
    }

    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
