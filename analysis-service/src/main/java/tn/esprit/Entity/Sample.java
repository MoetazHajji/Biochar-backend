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
@Table( name = "Sample")
public class Sample implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSample")
    private int idSample;
    private Date date;
    private String numSample;
    @OneToOne
    Test test;
    @ManyToOne
    Patient patient;


}
