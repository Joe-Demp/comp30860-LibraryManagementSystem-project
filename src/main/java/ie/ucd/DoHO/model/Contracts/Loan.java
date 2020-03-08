package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

@Entity
public class Loan implements Serializable, Comparable<Loan> {
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
    private Status status;

    public Loan() {
    }

    /**
     * Note: this constructor alters Artifact
     */
    public Loan(User user, Artifact artifact, Date due) {
        setUser(user);
        setArtifact(artifact);
        setDue(due);
        artifact.loan();
        computeStatus();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Loan other) {
        this.computeStatus();
        other.computeStatus();

        // If both have the same status, then compare due dates
        if (this.status == other.status) {
            if (this.status == Status.DUE) {
                // Artifacts due with sooner due dates will rise to the top
                return this.due.compareTo(other.due);
            } else {
                // Overdue and Returned artifacts have later dates first
                return -this.due.compareTo(other.due);
            }
        }
        // Otherwise compare by status
        return this.status.getValue() - other.status.getValue();
    }

    public void computeStatus() {
        if (isActive()) {
            if (isOverdue()) {
                this.status = Status.OVERDUE;
            } else {
                this.status = Status.DUE;
            }
        } else {
            this.status = Status.RETURNED;
        }
    }

    public String status() {
        computeStatus();
        return this.status.toString();
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

    public Status getStatus() {
        return status;
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

    public void renew(){
        Date currentDue = getDue();
        System.out.println(currentDue);
        Calendar c = Calendar.getInstance();
        c.setTime(currentDue);
        c.add(Calendar.DATE, 7);
        Date newDue = c.getTime();
        System.out.println(newDue);
        setDue(newDue);

    }

    public enum Status {
        OVERDUE(0, "OVERDUE"),
        DUE(1, "DUE"),
        RETURNED(2, "RETURNED");

        private final int value;
        private final String string;

        Status(int value, String string) {
            this.value = value;
            this.string = string;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return string;
        }


    }
}
