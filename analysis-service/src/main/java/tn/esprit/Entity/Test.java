package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table( name = "Test")
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTest")
    private int idTest;
    private String nameTest;
    private float price;
    @OneToOne(mappedBy = "test")
    Sample sample;
    @OneToOne
    TestResult testResult;
}
