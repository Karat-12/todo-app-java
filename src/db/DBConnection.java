package db;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/todo_app";
        String user = "root";
        String pass = "Root"; // change this

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}