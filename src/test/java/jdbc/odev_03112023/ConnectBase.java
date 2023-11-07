package jdbc.odev_03112023;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectBase {


    Connection conn;
    Statement stmnt;
    ResultSet rs;

    public ConnectBase() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/aaa?useSSL=false&serverTimezone=UTC";
        String username = "HC";
        String password = "admin";
            conn = DriverManager.getConnection(url, username, password );
            stmnt = conn.createStatement();

    }
    @AfterTest
    public void tearDown() throws SQLException {
        stmnt.close();
        conn.close();
    }

    public List<List<String>> getTable(String sql) {
        List<List<String>> table = new ArrayList<>();

        try {
            rs = stmnt.executeQuery(sql);
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= cols; i++) {
                    row.add(rs.getString(i));
                }
                table.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return table;
    }

}
