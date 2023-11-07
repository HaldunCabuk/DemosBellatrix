package jdbc.odev_03112023;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class ConnectBase {


    Connection conn;
    Statement stmnt;
    ResultSet rs;

    public ConnectBase() {

        String url = "jdbc:mysql://localhost:3306/aaa?useSSL=false&serverTimezone=UTC";
        String username = "HC";
        String password = "admin";

        try {
            conn = DriverManager.getConnection(url, username, password );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmnt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @AfterTest
    public void tearDown() throws SQLException {
        stmnt.close();
        conn.close();
    }
}
