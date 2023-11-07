package jdbc.odev_03112023;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.sql.SQLException;

/*
1.    Yeni bir Maven projesi olusturun
2.    Test icin Cucumber/Testng kullanabilirsiniz
3.    Bir veritabani baglantisi kurun
4.    local veritabaniniza baglanti kurup CREATE TABLE komutu ile asagidaki fieldlari iceren bir
    "adress" adinda bir tablo olusturun (CREATE TABLE icin arayüz kullanilmamalidir)
        id            Int AUTOINCREMENT
        first_name    String
        last_name    String
        company        String
        address1    String
        address2    String
        city        String
        postCode    String
        country        String
        state        String
    fieldlarini iceren bir tablo olusturun
5.    "adress" tablosuna country="Turkey", state="Adana", diger verileri de random degerler alarak
    INSERT INTO adress(first_name, ...) VALUES('',...) komutu ile bir kayit girin.
6.    "adress" tablosundaki state degerini "Ankara" olarak güncelleyin


 */
public class CreateAddressTable extends ConnectBase {

    static String firstName = "";
    static String lastName;
    static String company;
    static String address1;
    static String address2;
    static String city;
    static String postCode;
    static String country;
    static String state;


    @Test
    public void createTableAndDatas() throws SQLException {

        String newTableSql = " CREATE TABLE address (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  first_name VARCHAR(30) ,\n" +
                "  last_name VARCHAR(30) ,\n" +
                "  company VARCHAR(30) ,\n" +
                "  address1 VARCHAR(30) ,\n" +
                "  address2 VARCHAR(30) ,\n" +
                "  city VARCHAR(20) ,\n" +
                "  postCode VARCHAR(30) ,\n" +
                "  country VARCHAR(25) ,\n" +
                "  state VARCHAR(20) ,\n" +
                " PRIMARY KEY (id)\n" +
                ");";

        stmnt.execute(newTableSql);

        firstName = RandomStringUtils.randomAlphabetic(1).toUpperCase()
                + RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        lastName = RandomStringUtils.randomAlphabetic(3, 8).toUpperCase();
        company = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        address1 = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        address2 = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        city = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3, 8).toLowerCase();
        postCode = "0" + RandomStringUtils.randomNumeric(4);
        country = "Turkey";
        state = "Adana";

        String sqlInsert = "INSERT INTO address (first_name, last_name, company, address1, address2, city, postCode, country, state) " +
                "VALUES ('" + firstName + "', '" + lastName + "', '" + company + "', '" + address1 + "', " +
                "'" + address2 + "', '" + city + "', '" + postCode + "', '" + country + "', '" + state + "')";
        stmnt.execute(sqlInsert);

    }

    @Test
    public void update() throws SQLException {
        String sqlUpdate = "update address set state='Ankara';";
        stmnt.executeUpdate(sqlUpdate);


    }

    @Test
    public void getTable() throws SQLException {
        rs = stmnt.executeQuery("select id,first_name, last_name, company, address1, address2, city, postCode, country, state from address");

        while (rs.next()) {
            int id = rs.getInt(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            String company = rs.getString(4);
            String address1 = rs.getString("address1");
            String address2 = rs.getString(6);
            String city = rs.getString(7);
            String postCode = rs.getString("postCode");
            String country = rs.getString(9);
            String state = rs.getString(10);

            System.out.printf("%-5d%-15s%-15s%-15s%-3s\n", id, first_name, last_name, company, address1, address2, city, postCode, country, state);
        }

    }


    @Test
    public void deleteIfExist() throws SQLException {
        String deleteTable = "drop table address";
        stmnt.execute(deleteTable);
    }


}
