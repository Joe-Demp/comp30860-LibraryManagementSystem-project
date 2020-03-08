package ie.ucd.DoHO.model;


import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Indexed
public class EBook extends Book {
    @Column
    protected String hyperlink;

    public EBook() {
        setSubject("Ebook");
    }

    public EBook(String title, String author, String publisher, Integer releaseDate,
                 String genre, String libraryLocation, String language, int totalStock,
                 String ISBN, int pages, String hyperlink) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, totalStock, ISBN, pages);
        setSubject("EBook");
        setHyperlink(hyperlink);
    }

    public EBook(ArtifactForm form) {
        super(form);
        setSubject("EBook");
        setHyperlink(form.hyperlink);
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }
}
