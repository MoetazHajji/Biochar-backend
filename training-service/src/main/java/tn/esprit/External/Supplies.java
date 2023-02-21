package tn.esprit.External;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplies implements Serializable {

   // @Id
    int id;
    String unit;
    int quantity;
    @Temporal(TemporalType.DATE)
    Date date;
    String location;
    State state;

   // @OneToMany(mappedBy = "supplies")
   // Set<Product> products;
}
