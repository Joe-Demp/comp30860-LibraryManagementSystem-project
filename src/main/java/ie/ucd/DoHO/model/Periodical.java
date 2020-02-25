package ie.ucd.DoHO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Periodical extends Artifact {
    @Column
    private String frequency;
    @Column
    private int issue;

    public Periodical() {
    }

    public Periodical(String title, String author, String publisher, Date releaseDate, String genre,
                      String libraryLocation, String language, String frequency, int issue) {
        super(title, author, publisher, releaseDate, "Periodical", genre, libraryLocation, language);
        setFrequency(frequency);
        setIssue(issue);
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }
}
