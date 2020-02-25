package ie.ucd.DoHO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Book extends Artifact {
    @Column
    private String ISBN;
    @Column
    private boolean hardback;

    public Book() {
    }

    public Book(String title, String author, String publisher, Date releaseDate,
                String genre, String libraryLocation, String language, String ISBN, boolean hardback) {
        super(title, author, publisher, releaseDate, "Book", genre, libraryLocation, language);
        setISBN(ISBN);
        setHardback(hardback);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isHardback() {
        return hardback;
    }

    public void setHardback(boolean hardback) {
        this.hardback = hardback;
    }
}
