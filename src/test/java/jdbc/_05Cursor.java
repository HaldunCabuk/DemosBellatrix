package jdbc;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class _05Cursor extends BaseConnection{

    @Test
    public void test() throws SQLException {
        String sql = "SELECT * FROM actor WHERE actor_id<50";
        rs = stmnt.executeQuery(sql);


        rs.next();
        rs.next();
        rs.next();
        rs.next();
        System.out.println(rs.getString(2));

        rs.previous();
        System.out.println(rs.getString(2));

        rs.next();
        System.out.println(rs.getString(2));

        rs.first();
        System.out.println(rs.getString(2));

        rs.last();
        System.out.println(rs.getString(2));


        rs.absolute(10);
        System.out.println(rs.getString(2));



        rs.relative(10);
        System.out.println(rs.getString(2));



        rs.relative(-2);
        System.out.println(rs.getString(2));





        //rs.previous();
        //rs.first();
        //rs.last();
        //rs.relative(10);bulundugun yerden 10 ileri
        //rs.relative(-2);
        //rs.absolute(10);
    }


}
