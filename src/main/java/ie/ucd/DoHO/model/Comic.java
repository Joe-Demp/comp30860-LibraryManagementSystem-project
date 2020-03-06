package ie.ucd.DoHO.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Comic extends Magazine {
    public Comic() {
        setSubject("Comic");
    }

    public Comic(String title, String author, String publisher, Integer releaseDate, String genre,
                 String libraryLocation, String language, int totalStock, String frequency, int issue) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, totalStock, frequency, issue);
        setSubject("Comic");
    }
}
