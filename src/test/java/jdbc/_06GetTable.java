package jdbc;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class _06GetTable extends BaseConnection {

    @Test
    public void testGetTable() {
        String sql = "select * from personel where id<10";
        System.out.println(getTable(sql));


    }

    @Test
    public void testGetColumn() throws SQLException {
        String sql = "select * from actor where actor_id<10";
        System.out.println(getColumn(sql, "last_name"));
        System.out.println(getColumn(sql, 3));
    }

}
