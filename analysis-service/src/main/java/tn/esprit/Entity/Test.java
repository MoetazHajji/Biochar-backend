package tn.esprit.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table( name = "Test")
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTest")
    private int idTest;
    private String nameTest;
    private float price;
    private LocalDate dateTest;
    @ManyToOne
    Sample sample;
    @OneToMany( mappedBy="test")
    Set<TestResult> testResults;
}
