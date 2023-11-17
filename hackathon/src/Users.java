import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Users {
    int id;
    String f_name;
    String l_name;
    String email;
    String pass;
    String mobile;
    Date birth;

    public Users() {
    }

    public Users(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Users(int id, String f_name, String l_name, String email, String pass, String mobile, java.sql.Date birth) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.pass = pass;
        this.mobile = mobile;
        this.birth = new Date(birth.getTime());
    }

    public int getId() {
        return id;
    }


    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void signIn()
    {
        System.out.print("Enter Email Id : ");
        email = new Scanner(System.in).next();

        System.out.print("Enter pass : ");
        pass = new Scanner(System.in).next();
    }

    public void acceptUser() throws ParseException, RuntimeException {
        System.out.print("Enter first name : ");
        String f_name = new Scanner(System.in).nextLine();
        if(!f_name.trim().equals(""))
            setF_name(f_name);
        else
            throw new RuntimeException("empty name!!");

        System.out.print("Enter last name : ");
        String l_name = new Scanner(System.in).nextLine();
        if(!l_name.trim().equals(""))
            setL_name(l_name);
        else
            throw new RuntimeException("empty name!!");

        System.out.print("Enter Email Id : ");
        String email = new Scanner(System.in).nextLine();

        if(!email.trim().equals("")) {
            if (Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches())
                setEmail(email);
            else
                throw new RuntimeException("invalid email");
        }
        else
            throw new RuntimeException("empty email!!");

        System.out.print("Enter pass : ");
        String pass = new Scanner(System.in).nextLine();
        if( Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$").matcher(pass).matches())
            setPass(pass);
        else
            throw new RuntimeException("password format is invalid!");

        System.out.print("Enter mobile : ");
        String mobile = new Scanner(System.in).nextLine();
        if(!mobile.trim().equals(""))
            setMobile(mobile);
        else
            throw new RuntimeException("empty mobile!!");

        System.out.print("Enter birth : ");
        String sBirth = new Scanner(System.in).next();
        setBirth( new SimpleDateFormat("dd-MM-yyyy").parse(sBirth));

    }
    public void editUser() throws ParseException {
        System.out.print("Enter new first name : ");
        String fname = new Scanner(System.in).nextLine();
        if(!fname.trim().equals(""))
            setF_name(fname);
        else
            throw new RuntimeException("empty name!!");

        System.out.print("Enter new last name : ");
        String lname = new Scanner(System.in).nextLine();
        if(!lname.trim().equals(""))
            setL_name(lname);
        else
            throw new RuntimeException("empty name!!");

        System.out.print("Enter new mobile  : ");
        String mob = new Scanner(System.in).nextLine();
        if(!mob.trim().equals(""))
            setMobile(mob);
        else
            throw new RuntimeException("empty mobile!!");


        System.out.print("Enter new birth date : ");
        String sBirth = new Scanner(System.in).nextLine();
        setBirth(new SimpleDateFormat("dd-MM-yyyy").parse(sBirth));
    }

    public void changePassword()
    {
        System.out.print("Enter new password : ");
        String pass = new Scanner(System.in).nextLine();
        if( Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$").matcher(pass).matches())
            setPass(pass);
        else
            throw new RuntimeException("password format is invalid!");

    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birth=" + birth +
                '}';
    }
}
