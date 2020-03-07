package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    public Loan(User user, Artifact artifact, Date due) {
        setUser(user);
        setArtifact(artifact);
        // todo consider the possibility of having a standard loan period here and computing due
        setDue(due);
    }

    public String status() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dueDate = sdf.parse(getDue().toString().substring(0,10));
        Date returnedDate;
        if(returned != null){
            returnedDate = sdf.parse(getReturned().toString().substring(0,10));
            long diffInMillies = dueDate.getTime() - returnedDate.getTime() ;
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff >= 0){
                return "RETURNED";
            }
            else{
                return "OVERDUE";
            }
        }else{
            return "DUE";
        }
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

    public boolean isActive() {
        return returned == null;
    }

    public boolean isOverdue() {
        return due.before(Calendar.getInstance().getTime());
    }
}
