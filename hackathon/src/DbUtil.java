import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon", "D6_80621_KUSHAGRA", "sunbeam");
    }
}
