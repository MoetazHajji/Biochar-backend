package tn.esprit.Entity;

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
public class Supplies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String unit;
    @NonNull
    Long quantity;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date date;
    @NonNull
    String location;

    @Enumerated(EnumType.STRING)
    State state;

    @OneToMany(mappedBy = "supplies")
    List<Product> products;

}
