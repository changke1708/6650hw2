package com.example.dal;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * ConnectionManager uses the MySQL Connector/J driver to connect to MySQL instance.
 */
public class ConnectionManager {

    private final static String user = "mysqladmin";
    private final static String password = "mysqlpassword";
    private final static String hostName = "mysqlvip.c2svqzdvph3v.us-west-2.rds.amazonaws.com";
    private final static int port= 3306;
    private final static String schema = "bsds";

    private static DataSource dataSource = setupDataSource();

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static DataSource setupDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:mysql://" + hostName + ":" + port + "/" + schema);
        cpds.setUser(user);
        cpds.setPassword(password);
        cpds.setMinPoolSize(1);
        cpds.setAcquireIncrement(1);
        cpds.setMaxPoolSize(60);
        return cpds;
    }
}
