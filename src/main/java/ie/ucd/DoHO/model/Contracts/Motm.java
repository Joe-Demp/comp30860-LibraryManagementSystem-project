package ie.ucd.DoHO.model.Contracts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Motm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 1;
    @Column(length = 1000)
    private String bodyOfText = "";

    public Motm(){}

    public Motm(String bodyOfText){}

    public String getBodyOfText() {
        return bodyOfText;
    }

    public void setBodyOfText(String bodyOfText){
        this.bodyOfText = bodyOfText;
    }
}
