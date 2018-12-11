package com.example.dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class UtilDao {
    private ConnectionManager connectionManager;

    // Singleton pattern
    private static UtilDao instance = null;
    private UtilDao() {
        connectionManager = new ConnectionManager();
    }
    public static UtilDao getInstance() {
        if(instance == null) {
            instance = new UtilDao();
        }
        return instance;
    }

    public void cleanTable() throws SQLException {

        String deleteStepCounts = "TRUNCATE TABLE StepCounts;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteStepCounts);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
