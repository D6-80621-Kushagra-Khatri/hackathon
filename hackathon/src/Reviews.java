import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Reviews {
	private int id;
	private int movieId;
	private String review;
	private int rating;
	private int userId;
	private Date modified;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	@Override
	public String toString() {
		return String.format("Reviews [id=%s, movieId=%s, review=%s, rating=%s, userId=%s, modified=%s]", id, movieId,
				review, rating, userId, new SimpleDateFormat("hh:mm:ss--dd/MM/yyyy").format(modified));
	}

	public Reviews() {
	}

	public Reviews(int id) {
		this.id = id;
	}

	public Reviews(int id, int movieId, String review, int rating, int userId, Date modified) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.userId = userId;
		this.modified = modified;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reviews other = (Reviews) obj;
		return id == other.id;
	}

	public void acceptReview() throws SQLException {
		System.out.print("Enter movie id : ");
		int id = new Scanner(System.in).nextInt();
		setMovieId(id);

		if(!new MovieDao().displayAllMovies().contains(new Movies(id)))
			throw new RuntimeException("invalid movie id");

		System.out.print("Enter review : ");
		String review = new Scanner(System.in).nextLine();
		if(review.trim().equals(""))
			throw new RuntimeException("review cannot be empty!!");
		setReview(review);

		System.out.print("Enter rating : ");
		int rating = new Scanner(System.in).nextInt();
		setRating(rating);
	}

	public void editReview()
	{
		System.out.print("Enter new review : ");
		String review = new Scanner(System.in).nextLine();
		if(review.trim().equals(""))
			throw new RuntimeException("review cannot be empty!!");
		setReview(review);

		System.out.print("Enter new rating : ");
		int rating = new Scanner(System.in).nextInt();
		setRating(rating);

	}
	
	
}
