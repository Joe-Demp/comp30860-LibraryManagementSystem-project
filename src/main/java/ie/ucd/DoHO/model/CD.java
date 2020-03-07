package ie.ucd.DoHO.model;

import ie.ucd.DoHO.util.Formatter;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
public class CD extends Artifact {
    protected Integer runtimeMinutes = 0;

    public CD() {
        setSubject("CD");
    }

    public CD(ArtifactForm form) {
        super(form);
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