package ie.ucd.DoHO.model;

import ie.ucd.DoHO.util.Formatter;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.*;

/**
 * @author Department of Homeland Obscurity
 * <p>
 * Class to model common attributes of all Artifacts
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Artifact implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Set<String> primaryFields = new HashSet<>();

    static {
        primaryFields.addAll(
                Arrays.asList("title", "author", "publisher", "releaseDate", "subject", "genre")
        );
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String publisher;
    @Column
    private Date releaseDate;
    @Column
    private String subject;
    @Column
    private String genre;
    @Column
    private String libraryLocation;
    @Column
    private String language;

    public Artifact() {
    }

    /**
     * An overloaded constructor to facilitate adding Artifacts to the LMS
     */
    public Artifact(String title, String author, String publisher, Date releaseDate,
                    String subject, String genre, String libraryLocation, String language) {
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setReleaseDate(releaseDate);
        setSubject(subject);
        setGenre(genre);
        setLibraryLocation(libraryLocation);
        setLanguage(language);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLibraryLocation() {
        return libraryLocation;
    }

    public void setLibraryLocation(String libraryLocation) {
        this.libraryLocation = libraryLocation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, String> getAdditionalDetails() {
        Map<String, String> additionals = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            String fieldString = field.getName();
            if (!primaryFields.contains(fieldString)) {
                try {
                    fieldString = Formatter.toKeyString(fieldString);
                    Object value = field.get(this);
                    String valueString = value == null ? "null" : value.toString();
                    additionals.put(fieldString, valueString);
                } catch (IllegalAccessException e) {
                    System.err.printf("Field %s could not be added to additionalDetails", fieldString);
                    e.printStackTrace();
                }
            }
        }
        return additionals;
    }

    public String getReleaseDateString() {
        if (releaseDate == null) {
            return "null";
        }
        return Formatter.toDateString(releaseDate);
    }
}
