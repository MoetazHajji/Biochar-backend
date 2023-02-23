package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table( name = "Sample")
public class Sample implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSample")
    private Integer idSample;
    private Date date;
    private String numSample;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="sample")
    @JsonIgnore
    Set<Test> tests;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    Account account;


}
