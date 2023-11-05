package jdbc;

import org.testng.annotations.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

public class _04MetaData extends BaseConnection{

   ResultSetMetaData rsmd;
    @Test
    public void resultSetMetaData() throws SQLException {

        String url = "select * from personel";
        rs = stmnt.executeQuery(url);

        rsmd = rs.getMetaData();
        System.out.println("rsmd = " + rsmd.getColumnName(1));
        System.out.println("rsmd = " + rsmd.getColumnCount());

        int colm = rsmd.getColumnCount();

        for (int i = 1; i < colm; i++) {
            System.out.println(rsmd.getColumnName(i) + "\t" + rsmd.getColumnDisplaySize(i));

        }


    }

    @Test
    public void resultSetMetaData2() throws SQLException {

        String sql = "select id, first_name as name, last_name, email, gender, country, age " +
                "from personel where id<10;";
        rs = stmnt.executeQuery(sql);

        rsmd = rs.getMetaData();

        int colm = rsmd.getColumnCount();

        for (int i = 1; i <= colm; i++) {
            System.out.print(rsmd.getColumnLabel(i) + "\t" );// atadigimiz ismini getirir

        }
            while (rs.next()){
                for (int i = 1; i <=colm ; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

    }
    @Test
    public void resultSetMetaData3() throws SQLException {

        //String sql = "SELECT id, first_name AS Name, last_name AS LastName, email, gender, country, age AS Yas" +
        //        " FROM personel" +
        //        " WHERE id<10";
        String sql = "SELECT * FROM actor WHERE actor_id<20";
        rs = stmnt.executeQuery(sql);

        rsmd = rs.getMetaData();

        int cols = rsmd.getColumnCount();

        String format = "";

        String[] header = new String[cols];

        for (int i = 1; i <= cols; i++) {
            format += "%-" + rsmd.getColumnDisplaySize(i) + "s";
            header[i-1] = rsmd.getColumnLabel(i);
        }
        format += "\n";
        System.out.printf(format, header);

        String[] data = new String[cols];
        while (rs.next()){
            for (int i = 1; i <= cols ; i++) {
                data[i-1] = rs.getString(i);
            }
            System.out.printf(format, data);
        }
    }


    @Test
    public void formatWrite(){

        String format = "%-8s%-6d%.2f\n";
        Object[] arr = {"Ali",10, 1.3};
        System.out.printf(format, arr);
    }

}
