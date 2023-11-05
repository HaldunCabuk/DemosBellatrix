package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


    }

    @AfterTest
    public void afterTest() throws SQLException {
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
    public List<String> getColumn(String sql, String header) throws SQLException {
    List<String> col = new ArrayList<>();
    rs = stmnt.executeQuery(sql);
    while (rs.next()){
        col.add(rs.getString(header));
    }
    return col;
    }
    public List<String> getColumn(String sql, int index) throws SQLException {
        List<String> col = new ArrayList<>();
        rs = stmnt.executeQuery(sql);
        while (rs.next()){
            col.add(rs.getString(index));
        }
        return col;
    }
}
