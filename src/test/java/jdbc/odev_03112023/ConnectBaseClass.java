package jdbc.odev_03112023;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class ConnectBaseClass {


    Connection conn;
    Statement stmnt;
    ResultSet rs;

    @BeforeTest
    public void beforetest() throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306/aaa?useSSL=false&serverTimezone=UTC"; //127.0.0.1 yerine localhost yazilabilir
        String username = "HC";
        String pwd = "admin";

        conn = DriverManager.getConnection(url, username, pwd);

        stmnt = conn.createStatement();

    }

    @AfterTest
    public void close() throws SQLException {
    conn.close();
    stmnt.close();

    }
}
