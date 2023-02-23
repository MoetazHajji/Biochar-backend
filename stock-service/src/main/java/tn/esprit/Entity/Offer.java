package tn.esprit.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date start_date;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date end_date;
    @NonNull
    String quantity;
    @NonNull
    Double prix;

    @ManyToOne
    Product product;

    @ManyToOne
    Supplier supplier;
}
