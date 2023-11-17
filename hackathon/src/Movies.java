import java.util.Date;
import java.util.Objects;

public class Movies {
    int id;
    String title;
    Date released;


    public Movies(int id) {
        this.id = id;
    }

    public Movies(int id, String title, Date released) {
        this.id = id;
        this.title = title;
        this.released = released;
    }

    @Override
    public String toString() {
        return "movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", released=" + released +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return id == movies.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
