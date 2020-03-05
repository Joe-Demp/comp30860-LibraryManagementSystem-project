package ie.ucd.DoHO.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Indexed
public class Book extends Artifact {
    @Column
    protected String ISBN;
    @Column
    protected Integer pages;

    public Book() {
    }

    public Book(String title, String author, String publisher, Date releaseDate,
                String genre, String libraryLocation, String language, int totalStock,
                String ISBN, int pages) {
        super(title, author, publisher, releaseDate, "Book", genre, libraryLocation, language, totalStock);
        setISBN(ISBN);
        setPages(pages);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
