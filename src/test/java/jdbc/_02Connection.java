package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _02Connection {
    /*
    @BeforeTest -> connection ve statement definition
    @Test       -> personel tablosunda ulkeler nufuslarini listeleyin
                   tablo sonuna da genel toplami ekle
    @AfterTest  -> statement connection close
     */


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

    @Test
    public void test1() throws SQLException {
    String sql = "select country, count(*) as nufus from personel group by country " +
            " union " +
            " select 'Toplam', count(*) from personel";

        rs = stmnt.executeQuery(sql);

        while (rs.next()){
            System.out.printf("%-40s%d\n", rs.getString(1), rs.getInt(2));
        }

    }


    @AfterTest
    public void afterTest() throws SQLException {
        stmnt.close();
        conn.close();
    }
}
