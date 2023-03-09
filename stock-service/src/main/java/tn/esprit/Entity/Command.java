package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    LocalDate date;
    @NonNull
    String notice;
    @NonNull
    Long quantity_product;
    @NonNull
    Long NbPoduct;


    Double Total_price;

    /*@ManyToMany
    List<Product> products;*/

    @JsonIgnore
    @OneToMany(mappedBy = "command")
    Set<CommandLigne> commandLignes;
}
