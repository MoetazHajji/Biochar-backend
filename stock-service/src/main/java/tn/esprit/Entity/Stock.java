package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString
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
    Double storage;

    Double free_storage;

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


    @ManyToMany
    List<Product> products;

}
