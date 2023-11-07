package odevJdbcWithCucumber.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyConnection {

    private String url;
    private String username;
    private String password;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public MyConnection() {

    }

    public MyConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connect();
    }

    public void setCredentials(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connect();
    }

    private void connect(){
        if (url == null) throw new RuntimeException("DB url eksik");
        if (username == null) throw new RuntimeException("DB username eksik");
        if (password == null) throw new RuntimeException("DB password eksik");
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void execute(String sql){
        if (conn == null) connect();

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void closeConnection(){
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<List<String>> getTable(String sql) {
        try {
            List<List<String>> table = new ArrayList<>();
            rs = stmt.executeQuery(sql);
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()){
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= cols ; i++) {
                    row.add(rs.getString(i));
                }
                table.add(row);
            }
            return table;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
            jdbc:mysql://localhost:3306/guidersoft


            if (!url.matches("jdbc:[(\\w+)|(\\w+):(\\w+)]://(w+):(\\d+)/(\\w+)"))
                throw new RunTimeException("Hatali url")

            jdbc:mysql://username:password@localhost:3306/guidersoft

            conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/guidersoft",
                        "username",
                        "password
            );
     */


}
