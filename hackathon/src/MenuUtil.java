import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUtil {

    public final int mainMenu()
    {
        System.out.println("\n0.Exit");
        System.out.println("1.Sign Up");
        System.out.println("2.Sign In");
        return new Scanner(System.in).nextInt();
    }
    public final int menu()
    {
        System.out.println("\n0. Log Out");
        System.out.println("1. Edit Profile");
        System.out.println("2. Change Password");
        System.out.println("3. Write a Review ");
        System.out.println("4. Edit Review");
        System.out.println("5. Display all Movies");
        System.out.println("6. Display all Reviews");
        System.out.println("7. Display my Reviews");
        System.out.println("8. Display Reviews shared with me");
        System.out.println("9. Share a Review");
        System.out.println("10. Delete a Review");
        return new Scanner(System.in).nextInt();

    }
    public void userFunctions() throws Exception {
        int choiceMain;
        Users currentUser = null;
        while((choiceMain = mainMenu()) != 0)
        {
            if(choiceMain==1)
            {
                try {
                    currentUser = new Users();
                    currentUser.acceptUser();
                    System.out.println("Rows Affected : " + new UserDao().signUpUser(currentUser));
                    continue;
                }
                catch (ParseException e)
                {
                    System.out.println("invalid date entered");
                    continue;
                }
                catch (RuntimeException e)
                {
                    System.out.println(e.getMessage());
                    continue;
                }
                catch (SQLException e) {
                    System.out.println(":(( User already present");
                    continue;
                }
            }
            else {
                currentUser = new Users();
                currentUser.signIn();
                try {
                    currentUser = new UserDao().signInUser(currentUser);
                }
                catch (RuntimeException r)
                {
                    System.out.println(r.getMessage());
                    continue;
                }
            }
            if (currentUser == null)
                return;
            int choice;
            Reviews r;
            while ((choice = menu()) != 0) {
                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Rows affected : " + new UserDao().editUser(currentUser));

                        }catch (ParseException e)
                        {
                            System.out.println("invalid date entered");
                            break;
                        }
                        catch (RuntimeException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        catch (SQLException e) {
                            System.out.println("invalid values");
                            break;
                        }
                        break;

                    case 2:
                        try{
                            System.out.println("Rows affected : " + new UserDao().updatePass(currentUser));
                        }
                        catch(RuntimeException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3: {
                        r = new Reviews();
                        try {
                            r.acceptReview();

                            r.setUserId(currentUser.getId());

                            System.out.println("Rows affected : " + new ReviewsDao().createNewReview(r));
                        } catch (RuntimeException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                        break;

                    case 4: {
                        try {
                            System.out.print("Review id to edit : ");
                            int id = new Scanner(System.in).nextInt();
                            List<Reviews> reviews = new ReviewsDao().displayMyReviews(currentUser.getId());
                            if (reviews.contains(new Reviews(id))) {
                                r = reviews.get(reviews.indexOf(new Reviews(id)));
                                r.editReview();
                                System.out.println("Rows affected : " + new ReviewsDao().updateExistingReview(r));
                            } else
                                System.out.println("Invalid review id");
                        }catch (RuntimeException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;

                    case 5:
                        ArrayList<Movies> moviesList = new MovieDao().displayAllMovies();
                        if (moviesList != null)
                            moviesList.forEach(System.out::println);
                        break;

                    case 6: {
                        List<Reviews> list = new ReviewsDao().displayAllReviews();
                        list.forEach(System.out::println);
                    }
                        break;

                    case 7: {
                        List<Reviews> list = new ReviewsDao().displayMyReviews(currentUser.getId());
                        list.forEach(System.out::println);
                    }
                        break;

                    case 8:
                    {
                        List<Reviews> list = new ReviewsDao().displaySharedWithMeReviews(currentUser.getId());
                        list.forEach(System.out::println);
                    }
                        break;

                    case 9:
                    {
                        System.out.print("user id to share with : ");
                        int newId = new Scanner(System.in).nextInt();
                        if(newId== currentUser.getId()) {
                            System.out.println("Invalid!! cannot share with yourself");
                            break;
                        }
                        System.out.print("review id to share : ");
                        int reviewId = new Scanner(System.in).nextInt();
                        List<Reviews> reviews = new ReviewsDao().displayMyReviews(currentUser.getId());
                        if(reviews.contains(new Reviews(reviewId)))
                        {
                            System.out.println("Rows affected : " + new ReviewsDao().shareReviewWithOthers(reviewId, newId));
                        } else
                            System.out.println("Invalid review id");
                    }
                    break;

                    case 10:
                    {
                        System.out.print("review id to delete : ");
                        int reviewId = new Scanner(System.in).nextInt();
                        List<Reviews> reviews = new ReviewsDao().displayMyReviews(currentUser.getId());
                        if(reviews.contains(new Reviews(reviewId)))
                        {
                            System.out.println("Rows affected : " + new ReviewsDao().deleteExistingReview(reviewId, currentUser.getId()));
                        } else
                            System.out.println("Invalid review id");
                    }
                    break;
                }
            }
        }

    }
}
