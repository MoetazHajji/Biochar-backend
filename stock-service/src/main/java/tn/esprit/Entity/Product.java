package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name_product;
    Double price;
    String description;

    @Enumerated(EnumType.STRING)
    Type_product type_product;

    @ManyToOne
    Supplies supplies;

    @ManyToMany(mappedBy = "products")
    List<Command> commands;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Offer> offers;
}
