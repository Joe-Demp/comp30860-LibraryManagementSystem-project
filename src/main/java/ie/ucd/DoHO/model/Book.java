package ie.ucd.DoHO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Book extends Artifact {
    @Column
    protected String ISBN;
    @Column
    protected int pages;

    public Book() {
    }

    public Book(String title, String author, String publisher, Date releaseDate,
                String genre, String libraryLocation, String language, String ISBN, int pages) {
        super(title, author, publisher, releaseDate, "Book", genre, libraryLocation, language);
        setISBN(ISBN);
        setPages(pages);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
