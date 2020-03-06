package ie.ucd.DoHO.model;

import javax.persistence.Entity;

@Entity
public class Magazine extends Periodical {
    public Magazine() {
    }

    public Magazine(String title, String author, String publisher, Integer releaseDate, String genre,
                    String libraryLocation, String language, int totalStock, String frequency, int issue) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, totalStock, frequency, issue);
        setSubject("Magazine");
    }
}
