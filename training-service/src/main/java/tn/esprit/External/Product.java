package tn.esprit.External;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    //@Id
    int id;
    String name_product;
    String designation;
    String reference;
    Type_product type_product;

    @ManyToOne
    Supplies supplies;
}
