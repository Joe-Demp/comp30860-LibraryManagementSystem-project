package ie.ucd.DoHO.model;

import ie.ucd.DoHO.util.Formatter;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
@Indexed
public class Video extends Artifact {
    protected Integer runtimeMinutes = 0;

    public Video() {
        setSubject("Video");
    }

    public Video(ArtifactForm form) {
        setRuntimeMinutes(form.runtimeMinutes);
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes > 0 ? runtimeMinutes : 0;
    }

    public String runtimeString() {
        Duration d = Duration.ofMinutes(runtimeMinutes);
        return Formatter.toDurationString(d);
    }
}
