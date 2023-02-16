package tn.esprit.Entity;

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
    private String Description;
    private String Resultat;
    private Date date;
    @OneToOne(mappedBy = "testResult")
    Test test;





}
