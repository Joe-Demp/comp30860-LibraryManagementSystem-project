package ie.ucd.DoHO.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;

@Entity
@Indexed
public class Book extends Artifact {
    protected String ISBN = "";
    protected Integer pages = 0;

    public Book() {
        setSubject("Book");
    }

    public Book(String title, String author, String publisher, Integer releaseYear,
                String genre, String libraryLocation, String language, Integer totalStock,
                String ISBN, Integer pages) {
        super(title, author, publisher, releaseYear, "Book", genre, libraryLocation, language, totalStock);
        setISBN(ISBN);
        setPages(pages);
    }

    public Book(ArtifactForm form) {
        super(form);
        setISBN(form.ISBN);
        setPages(form.pages);
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
