import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao implements AutoCloseable{
	private Connection con;
	private PreparedStatement createReview;
	private PreparedStatement updateReview;
	private PreparedStatement deleteReview;
	private PreparedStatement displayReviewsAll;
	private PreparedStatement displayReviewsMy;
	private PreparedStatement displayReviewsSharedWithMe;
	private PreparedStatement shareReview;
	
	public ReviewsDao() throws SQLException {
		con = DbUtil.getConnect();
		String sql = "INSERT INTO reviews values (DEFAULT,?,?,?,?, now())";
		createReview = con.prepareStatement(sql);
		sql = "update reviews set review=?, rating = ?,modified=now() where reviews.user_id = ? and reviews.id = ?";
		updateReview = con.prepareStatement(sql);
		sql = "DELETE FROM reviews WHERE id=? and user_id=?";
		deleteReview = con.prepareStatement(sql);
		sql = "SELECT * from reviews";
		displayReviewsAll = con.prepareStatement(sql);
		sql = "SELECT * from reviews where reviews.user_id = ?";
		displayReviewsMy = con.prepareStatement(sql);
		sql = "select * from reviews where reviews.id in (select review_id from shares where user_id=?)";
		displayReviewsSharedWithMe = con.prepareStatement(sql);
		sql = "INSERT INTO shares values (?,?)";
		shareReview = con.prepareStatement(sql);
	}
	
	public int createNewReview(Reviews r)throws Exception {
		createReview.setInt(1, r.getMovieId());
		createReview.setString(2, r.getReview());
		createReview.setInt(3, r.getRating());
		createReview.setInt(4, r.getUserId());
		return createReview.executeUpdate();
	}
	
	public int updateExistingReview(Reviews r)throws Exception {
		updateReview.setString(1, r.getReview());
		updateReview.setInt(2, r.getRating());
		updateReview.setInt(3,r.getUserId());
		updateReview.setInt(4, r.getId());
		return updateReview.executeUpdate();
	}
	
	public int deleteExistingReview(int id, int userId)throws Exception {
		deleteReview.setInt(1,id);
		deleteReview.setInt(2, userId);
		return deleteReview.executeUpdate();
	}

	public List<Reviews> displayAllReviews() throws Exception{
		List<Reviews> list = new ArrayList<>();
		try(ResultSet rs = displayReviewsAll.executeQuery()){
			while(rs.next()) {
				list.add(new Reviews(rs.getInt("id"),rs.getInt("movie_id"),rs.getString("review"),rs.getInt("rating"),rs.getInt("user_id"),new Date(rs.getTimestamp("modified").getTime())));
			}
		}
		return list;
	}
	
	public List<Reviews> displayMyReviews(int userId) throws Exception{
		List<Reviews> list = new ArrayList<>();
		displayReviewsMy.setInt(1, userId);
		try(ResultSet rs = displayReviewsMy.executeQuery()){
			while(rs.next()) {
				list.add(new Reviews(rs.getInt("id"),rs.getInt("movie_id"),rs.getString("review"),rs.getInt("rating"),rs.getInt("user_id"),new Date(rs.getTimestamp("modified").getTime())));
			}
		}
		return list;
	}
	
	public List<Reviews> displaySharedWithMeReviews(int userId) throws Exception{
		List<Reviews> list = new ArrayList<>();
		displayReviewsSharedWithMe.setInt(1, userId);
		try(ResultSet rs = displayReviewsSharedWithMe.executeQuery()){
			while(rs.next()) {
				list.add(new Reviews(rs.getInt("id"),rs.getInt("movie_id"),rs.getString("review"),rs.getInt("rating"),rs.getInt("user_id"),new Date(rs.getTimestamp("modified").getTime())));
			}
		}
		return list;
	}
	
	public int shareReviewWithOthers(int reviewId, int userId)throws Exception {
		shareReview.setInt(1, reviewId);
		shareReview.setInt(2, userId);
		return shareReview.executeUpdate();
	}
	
	@Override
	public void close() throws Exception {
		if(shareReview != null) {
			shareReview.close();
		}
		if(displayReviewsSharedWithMe != null) {
			displayReviewsSharedWithMe.close();
		}
		if(displayReviewsMy != null) {
			displayReviewsMy.close();
		}
		if(displayReviewsAll != null) {
			displayReviewsAll.close();
		}
		if(deleteReview != null) {
			deleteReview.close();
		}
		if(updateReview != null) {
			updateReview.close();
		}
		if(createReview != null) {
			createReview.close();
		}
		if(con != null) {
			con.close();
		}
	}
}
