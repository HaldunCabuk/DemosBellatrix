package jdbc;

import com.mysql.cj.jdbc.CallableStatement;
import lombok.Data;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static io.restassured.RestAssured.*;

public class _09jdbcRest extends BaseConnection {
    // id, name, email, gender, status PRIMARY(id) olan bir tablo create edin
    PreparedStatement pstmt;
    @Test
    public void createTable() throws SQLException {
        String sql = "create table abcd" +
                "(\n" +
                "    id int NOT NULL,\n" +
                "    name varchar(45) NOT NULL,\n" +
                "    email varchar(100),\n" +
                "    gender varchar(45),\n" +
                "    status varchar(45),\n" +
                "    PRIMARY KEY(id)\n" +
                ");";
        stmnt.execute(sql);


    }

    @Test
    public void getFromGorest() throws SQLException {

        List<DataUser> users = get("https://gorest.co.in/public/v2/users")
                .jsonPath().getList("", DataUser.class);
        System.out.println(users.get(0).getId());
        String sqlInsert = "insert into abcd values (?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sqlInsert);

        for (DataUser user : users){
            pstmt.setInt(1,user.id);
            pstmt.setString(2,user.name);
            pstmt.setString(3,user.email);
            pstmt.setString(4,user.gender);
            pstmt.setString(5,user.status);
            pstmt.executeUpdate();
        }



    }






@Data
    public static class DataUser{
        public int id;
        public String name;
        public String email;
        public String gender;
        public String status;
    }


}
