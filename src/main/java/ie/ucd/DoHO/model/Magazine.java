package ie.ucd.DoHO.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Magazine extends Periodical {
    public Magazine() {
    }

    public Magazine(String title, String author, String publisher, Date releaseDate, String genre,
                    String libraryLocation, String language, String frequency, int issue) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, frequency, issue);
        setSubject("Magazine");
    }
}
