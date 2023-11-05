package jdbc;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class _07Queries extends BaseConnection {


    @Test
    public void deleteUpdate() throws SQLException {

        String sqlDrop = "drop table if exist personel1";
        stmnt.execute(sqlDrop);

        String sqlCreate = "CREATE TABLE personel1" +
                " SELECT id, first_name, last_name, gender, country, city, countrycode, age, length FROM personel;";

        stmnt.execute(sqlCreate);

        String sqlGetData = "select * from personel1 where id<10";
        System.out.println(getTable(sqlGetData));

        String sqlUpdate = "update personel1 set age=0;";

        int effectedRows = stmnt.executeUpdate(sqlUpdate);

        System.out.println(effectedRows);
        System.out.println(sqlGetData);

        String sqlDelete = "delete from personel1 where id%2=0";
        effectedRows = stmnt.executeUpdate(sqlUpdate);
    }

    /*
1) mytable adinda, id INT, adi VHARCHAR(30), soyadi VARCHAR(30),
yas INT olan bir tablo create edin
2) Tabloya 5 adet veri girin
3) tablodaki yaslari 2 ile carpin ve güncelleyin
4) tablodaki yasi 30 dan kücük olanlari silin
     */
    @Test
    public void createTable() throws SQLException {
        String sqlCreate = " CREATE TABLE mytable (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  first_name VARCHAR(30) ,\n" +
                "  last_name VARCHAR(30) ,\n" +
                "  age INT,\n" +
                "  PRIMARY KEY (id)\n" +
                ");";
        stmnt.execute(sqlCreate);
        System.out.println(getTable("select * from mytable"));

        String addData = "insert into mytable(first_name, last_name, age) values " +
                "('Haldun', 'Cabuk',44),('hasan','ak','22'),('hasan','ak','29'),('hasan','ak','15')";
        stmnt.executeUpdate(addData);
        System.out.println(getTable("select * from mytable"));

        String updateAge = "update mytable set age=age*2";
        stmnt.executeUpdate(updateAge);
        System.out.println(getTable("select * from mytable"));

        String delete = "delete from mytable where age>30";
        stmnt.executeUpdate(delete);
        System.out.println(getTable("select * from mytable"));

       // rs = stmnt.executeQuery("select * from mytable");
      


    }

}
