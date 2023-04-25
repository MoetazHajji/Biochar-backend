package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table( name = "TestResult")
public class TestResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTestResult")
    private int idTestResult;
    private String teest;
    private String resultat;
    private String unite;
    private String PLAGE_DE_REFERENCE;
    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    Test test;

    public TestResult(String text, String text1, String text2, String text3) {
        this.teest=String.valueOf(text);
        this.resultat=String.valueOf(text1);
        this.unite=String.valueOf(text2);
        this.PLAGE_DE_REFERENCE=String.valueOf(text3);
        this.date=new Date();
    }


}
