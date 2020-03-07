package ie.ucd.DoHO.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Periodical extends Artifact {
    @Column
    protected String frequency;
    @Column
    protected int issue;

    public Periodical() {
        setSubject("Periodical");
    }

    public Periodical(String title, String author, String publisher, Integer releaseDate, String genre,
                      String libraryLocation, String language, int totalStock, String frequency,
                      int issue) {
        super(title, author, publisher, releaseDate, "Periodical", genre, libraryLocation, language, totalStock);
        setFrequency(frequency);
        setIssue(issue);
    }

    public Periodical(ArtifactForm form) {
        super(form);
        setFrequency(form.frequency);
        setIssue(form.issue);
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
