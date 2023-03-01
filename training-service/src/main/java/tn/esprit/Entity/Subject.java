package tn.esprit.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.index.Indexed;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

//@Document(collection = "subject")
@Entity
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    @Indexed(unique = true)
    String title;
    String description;
    int complexity;
    int priority;
}
