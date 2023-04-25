package PLANE_DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
    public static Connection DBConnect(){
        Connection con = null;

        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        String user = "PC1";

        String password = "1111";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(url,user,password);
            System.out.println("DB 접속 성공!");

        } catch (ClassNotFoundException e) {
            System.out.println("DB접속 실패 : 드라이버 로딩실패!");
            throw new RuntimeException(e);

        } catch (SQLException e) {
            System.out.println("DB접속 실패 : 접속 정보 확인!");
            throw new RuntimeException(e);
        }
        return  con;
    }
}
