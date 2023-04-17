package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public Connection getConnect() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/application";
            String login = "postgres";
            String pass = "1432";
            Connection connection = DriverManager.getConnection(url, login, pass);
            if (connection == null) {
                System.out.println("Check the entered data to connect to the database \n");
                return null;
            } else {
                System.out.println("Connection to the 'Application' database is made \n");
                return connection;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
