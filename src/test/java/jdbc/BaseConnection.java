package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class BaseConnection {
    Connection conn;
    Statement stmnt;
    ResultSet rs;


    @BeforeTest
    public void beforeTest() throws SQLException {




        String url = "jdbc:mysql://127.0.0.1:3306/guidersoft";
        String username = "jdbc";
        String pwd = "jdbc123456";
        conn = DriverManager.getConnection(url, username, pwd);

        stmnt = conn.createStatement();



    }

    @AfterTest
    public void afterTest() throws SQLException {
        stmnt.close();
        conn.close();
    }
}
