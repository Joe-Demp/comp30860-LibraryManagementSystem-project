package ie.ucd.DoHO.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;

@Entity
@Indexed
public class Magazine extends Periodical {
    public Magazine() {
        setSubject("Magazine");
    }

    public Magazine(String title, String author, String publisher, Integer releaseDate, String genre,
                    String libraryLocation, String language, int totalStock, String frequency, int issue) {
        super(title, author, publisher, releaseDate, genre, libraryLocation, language, totalStock, frequency, issue);
        setSubject("Magazine");
    }

    public Magazine(ArtifactForm form) {
        super(form);
        setSubject("Magazine");
    }
}
