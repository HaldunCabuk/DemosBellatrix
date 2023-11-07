package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _10SqLite {
    Connection conn;
    Statement stmt;
    ResultSet rs;

    String url = "jdbc:sqlite:src/test/resources/data.sqlite";

    @BeforeTest
    public void beforeTest() throws SQLException {

        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();

    }


    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }

    @Test
    public void getDatas() throws SQLException {

        String sql = "select * from users ";

        rs = stmt.executeQuery(sql);

        while (rs.next()){

            String firstName  = rs.getString(2);
            String lastName = rs.getString(3);

            System.out.println(firstName + " " + lastName);

        }


    }

    @Test
    public void insertAUser() throws SQLException {
        String sql = "insert into Users (name, email, gender, age) values ('Ali','abc@gmail.com', 'male', 30 ) ";

        int num = stmt.executeUpdate(sql);

        System.out.println(num);


    }
    @Test
    public void createTable() throws SQLException {
        String sql = "create table users (id integer not null primary key autoincrement, name text, email text, gender text, age integer)";
        stmt.execute(sql);



    }

}
