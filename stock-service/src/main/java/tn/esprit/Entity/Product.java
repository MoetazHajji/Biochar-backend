package tn.esprit.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name_product;
    String designation;
    String reference;

    @Enumerated(EnumType.STRING)
    Type_product type_product;

    @ManyToOne
    Supplies supplies;

    @ManyToOne
    Command command;

    @OneToMany(mappedBy = "product")
    List<OfferCall> offerCalls;
}
