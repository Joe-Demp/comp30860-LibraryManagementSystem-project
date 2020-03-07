package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Artifact artifact;
    @CreationTimestamp
    private Date created;
    private Date due;
    private Date returned;

    public Loan() {
    }

    /**
     * Note: this constructor alters Artifact
     * todo see if the above fact is true
     */
    public Loan(User user, Artifact artifact, Date due) {
        setUser(user);
        setArtifact(artifact);
        setDue(due);
        artifact.loan();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }

    public boolean doReturn() {
        if (this.returned == null) {
            this.returned = Date.from(Instant.now());
            artifact.receive();
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return returned == null;
    }

    public boolean isOverdue() {
        return due.before(Date.from(Instant.now()));
    }
}
