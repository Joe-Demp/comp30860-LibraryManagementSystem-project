package ie.ucd.DoHO.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class EBook extends Book {
    @Column
    protected String hyperlink;

    public EBook() {
    }

    public EBook(String title, String author, String publisher, Date releaseDate,
                 String genre, String libraryLocation, String language, String ISBN, int pages,
                 String hyperlink) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, ISBN, pages);
        setSubject("E-Book");
        setHyperlink(hyperlink);
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }
}
