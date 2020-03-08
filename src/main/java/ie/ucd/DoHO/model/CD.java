package ie.ucd.DoHO.model;

import ie.ucd.DoHO.util.Formatter;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
@Indexed
public class CD extends Artifact {
    protected Integer runtimeMinutes = 0;

    public CD() {
        setSubject("CD");
    }

    public CD(ArtifactForm form) {
        super(form);
        setRuntimeMinutes(runtimeMinutes);
    }

    public CD(String title, String author, String publisher, Integer releaseYear,
                String genre, String libraryLocation, String language, Integer totalStock,
                Integer runtimeMinutes) {
        super(title, author, publisher, releaseYear, "CD", genre, libraryLocation, language, totalStock);
            setRuntimeMinutes(runtimeMinutes);
    }


    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes > 0 ? runtimeMinutes : 0;
    }

    // todo test this
    public String runtimeString() {
        Duration d = Duration.ofMinutes(runtimeMinutes);
        return Formatter.toDurationString(d);
    }
}