import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookstore?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "1234"   // use your password
            );

            System.out.println("CONNECTED SUCCESSFULLY");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}