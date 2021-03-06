package ie.ucd.DoHO.model;

import ie.ucd.DoHO.model.Contracts.Loan;
import ie.ucd.DoHO.model.Contracts.Reservation;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName = "";
    @Field(termVector = TermVector.YES)
    private String username = "";
    private String password = "";
    @Field(termVector = TermVector.YES)
    private String email = "";
    private String phoneNumber = "";
    private String role = "";
    @CreationTimestamp
    private Date created;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    public User() {

    }

    public User(String fullName, String username, String password, String email, String phoneNumber, String role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public Date getCreated() {
        return created;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public boolean isAdmin() {
        return this.role.equals("admin");
    }

    public boolean isMember() {
        return this.role.equals("member");
    }

    @Override
    public String toString() {
        return "{" +
                " username=" + getUsername() +
                " password=" + getPassword() +
                " email=" + getEmail() +
                " role=" + getRole() +
                " }";
    }
}
