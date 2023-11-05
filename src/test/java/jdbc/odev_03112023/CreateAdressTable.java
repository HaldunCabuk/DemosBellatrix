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
7.    https://opencart.abstracta.us/ sitesinde
    a. yeni bir kullanici register edin
    b. login olun
    c. Adress Book'a gidin
    d. Veritabanina eklediginiz kaydi SELECT komutu ile okuyun
    e. Aldiginiz bu veriler ile "Adress Book" kaydi yapin.
    f. Kayit yapildigindan emin olun.

8.    Yukaridaki islemleri yaptigi sürece,
    a. proje modeli
    b. BaseClass, methods
    c. TestFramework (JUnit, TestNG, Cucumber)
    d. ve ihtiyac hissettiginiz sizin inisiyatifinizdedir.
 */
public class CreateAdressTable extends ConnectBaseClass {

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

        String firstName = RandomStringUtils.randomAlphabetic(1).toUpperCase()
                + RandomStringUtils.randomAlphabetic(3,8).toLowerCase();
        String lastName = RandomStringUtils.randomAlphabetic(3,8).toUpperCase();
        String company = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3,8).toLowerCase();
        String adress1 = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3,8).toLowerCase();
        String adress2 = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3,8).toLowerCase();
        String city = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(3,8).toLowerCase();
        String postCode = "0" + RandomStringUtils.randomNumeric(4);
        String country = "Turkey";
        String state = "Adana";

        String sqlInsert= "INSERT INTO address (first_name, last_name, company, address1, address2, city, postCode, country, state) " +
                "VALUES ('" + firstName + "', '" + lastName + "', '" + company + "', '" + adress1 + "', '" + adress2 + "', '" + city + "', '" + postCode + "', '" + country + "', '" + state + "')";
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

    }


    @Test
    public void deleteIfExist() throws SQLException {
        String deleteTable = "drop table address";
        stmnt.execute(deleteTable);
    }

}
