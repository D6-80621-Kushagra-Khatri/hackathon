import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MovieDao implements AutoCloseable {

    Connection con;
    PreparedStatement displayAll;
    String sql;


    public MovieDao() throws SQLException {
        con = DbUtil.getConnect();
        sql = "SELECT * from movies";
        displayAll = con.prepareStatement(sql);
    }

    public ArrayList<Movies> displayAllMovies()
    {
        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        try(ResultSet rs = displayAll.executeQuery())
        {
            while(rs.next()) {

                moviesArrayList.add(new Movies(rs.getInt(1), rs.getString(2), new Date(rs.getDate(3).getTime())));
            }
            return moviesArrayList;

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return moviesArrayList;
    }

    @Override
    public void close() throws Exception {
        displayAll.close();
        con.close();
    }
}
