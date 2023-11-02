package jdbc;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class _Test03 extends BaseConnection {
    @Test
    public void getCountryPopulation() throws SQLException {

        String sql = "select country, count(*) as nufus from personel group by country " +
                " union " +
                " select 'Toplam', count(*) from personel";

        rs = stmnt.executeQuery(sql);

        while (rs.next()) {
            System.out.printf("%-40s%d\n", rs.getString(1), rs.getInt(2));


        }
    }
}
