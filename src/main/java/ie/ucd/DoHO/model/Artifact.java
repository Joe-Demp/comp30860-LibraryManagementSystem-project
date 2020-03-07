package ie.ucd.DoHO.model;

import ie.ucd.DoHO.model.Contracts.Loan;
import ie.ucd.DoHO.model.Contracts.Reservation;
import ie.ucd.DoHO.util.Formatter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Department of Homeland Obscurity
 * <p>
 * Class to model common attributes of all Artifacts
 */
@Entity
@Indexed
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Artifact implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Set<String> primaryFields = new HashSet<>();

    static {
        primaryFields.addAll(
                Arrays.asList("title", "author", "publisher", "releaseYear", "subject", "genre")
        );
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Field(termVector = TermVector.YES)
    private String title;
    @Column
    @Field(termVector = TermVector.YES)
    private String author;
    @Column
    @Field(termVector = TermVector.YES)
    private String publisher;
    @Column
    private Integer releaseYear;
    @Column
    @Field(termVector = TermVector.YES)
    private String subject;
    @Column
    @Field(termVector = TermVector.YES)
    private String genre;
    @Column
    @Field(termVector = TermVector.YES)
    private String libraryLocation;
    @Column
    @Field(termVector = TermVector.YES)
    private String language;
    @Column
    private int totalStock = 0;
    @Column
    private int stockOnLoan = 0;
    @OneToMany(mappedBy = "artifact")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "artifact")
    private List<Loan> loans;


    public Artifact() {
    }

    /**
     * An overloaded constructor to facilitate adding Artifacts to the LMS
     */
    public Artifact(String title, String author, String publisher, Integer releaseYear,
                    String subject, String genre, String libraryLocation, String language,
                    int totalStock) {
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setReleaseYear(releaseYear);
        setSubject(subject);
        setGenre(genre);
        setLibraryLocation(libraryLocation);
        setLanguage(language);
        setTotalStock(totalStock);
    }

    public Artifact(ArtifactForm form) {
        setTitle(form.title);
        setAuthor(form.author);
        setPublisher(form.publisher);
        setReleaseYear(form.releaseYear);
        setSubject(form.subject);
        setGenre(form.genre);
        setLibraryLocation(form.libraryLocation);
        setLanguage(form.language);
        setTotalStock(form.totalStock);
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseDate) {
        this.releaseYear = releaseDate;
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

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        // If stockOnLoan is less than the new totalStock
        //  reduce stockOnLoan to the new value too
        // todo implement a method to release some loans if the library decreases total stock
        if (this.stockOnLoan > totalStock) {
            stockOnLoan = totalStock;
        }
        this.totalStock = totalStock;
    }

    public int getStockOnLoan() {
        return stockOnLoan;
    }

    public void setStockOnLoan(int stockOnLoan) {
        this.stockOnLoan = stockOnLoan;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public Map<String, String> getAdditionalDetails() {
        Map<String, String> additionals = new HashMap<>();
        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
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

    public boolean isAvailable() {
        return stockOnLoan < totalStock;
    }

    public void loan() {
        if (stockOnLoan < totalStock) {
            stockOnLoan++;
        }
    }

    public void receive() {
        if (stockOnLoan > 0) {
            stockOnLoan--;
        }
    }
}
