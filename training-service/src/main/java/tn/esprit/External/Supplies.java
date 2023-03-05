package tn.esprit.External;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

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
