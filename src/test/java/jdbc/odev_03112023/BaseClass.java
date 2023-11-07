package jdbc.odev_03112023;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class BaseClass {
    Connection conn;
    Statement stmnt;
    ResultSet rs;

    @BeforeTest
    public void beforetest() throws SQLException {

        String url = "jdbc:mysql://opencart.abstracta.us/index.php?route=account/address"; //127.0.0.1 yerine localhost yazilabilir

        String username = "a1b2c3@az.de";
        String pwd = "admin";
        conn = DriverManager.getConnection(url, username, pwd);

        stmnt = conn.createStatement();

    }


    @AfterTest
    public void afterTest() throws SQLException {
        conn.close();
        stmnt.close();

    }
}
