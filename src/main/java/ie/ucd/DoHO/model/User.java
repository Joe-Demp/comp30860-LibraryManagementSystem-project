package ie.ucd.DoHO.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @CreationTimestamp
    private Date profileCreated;
    private String email;
    private String type;

    public User(String username, String email, String type) {
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getProfileCreated() {
        return profileCreated;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileCreated(Date profileCreated) {
        this.profileCreated = profileCreated;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }
}
