package jdbc;

import org.testng.annotations.Test;

import java.sql.*;

public class _01Connection {
    /*
    jdbc
        connection : baglanti icin,  yol
        statement  : statement,      firma
        resultSet  : kayit kumesi    arac



     */

    @Test
    public void test_Connection1() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://127.0.0.1:3306/guidersoft?useSSL=false&serverTimezone=UTC"; //127.0.0.1 yerine localhost yazilabilir
        String username = "jdbc";
        String pwd = "jdbc123456";

        // veritabanina connection olusturulur
        Connection conn = DriverManager.getConnection(url,username,pwd);

        Statement stmnt = conn.createStatement();

        //tablodaki datalar SQL ile ResultSet e aliyoruz.
        ResultSet rs = stmnt.executeQuery("select id, first_name, last_name, country, age from personel");
/*
        stmnt.executeQuery(); return ResultSet  : select
        stmnt.execute();      return boolean    : create, alter
        stmnt.executeUpdate();return int        : update, delete, kac kayit eklendi

  */

        while (rs.next()){
            int id = rs.getInt(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString("last_name");
            String country = rs.getString(4);
            String age = rs.getString("age");

            System.out.printf("%5d%-10s%-15s%-15s%-3s\n", id, first_name, last_name, country, age);

        }

        stmnt.close();
        conn.close();
    }



}
