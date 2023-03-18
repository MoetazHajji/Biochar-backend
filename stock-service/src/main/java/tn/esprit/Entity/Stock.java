package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String unit;
    @NonNull
    Long nbProduct;
    @NonNull
    Double total_quantity;

    @NonNull
    @Min(0)
    Double storage;

    @Min(0)
    Double free_storage;

    @Min(0)
    Double used_storage;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date date;
    @NonNull
    String location;

    @NonNull
    @Enumerated(EnumType.STRING)
    Type_product type_product;
    @Enumerated(EnumType.STRING)
    State state;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    Set<Product> products;

}
