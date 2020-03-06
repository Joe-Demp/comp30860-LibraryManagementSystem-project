package ie.ucd.DoHO.model;

import ie.ucd.DoHO.util.Formatter;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
public class Video extends Artifact {
    private Integer runtimeMinutes = 0;

    public Video() {
        setSubject("Video");
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String runtimeString() {
        Duration d = Duration.ofMinutes(runtimeMinutes);
        return Formatter.toDurationString(d);
    }
}
