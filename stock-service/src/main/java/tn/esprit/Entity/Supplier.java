package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor  @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String name_supplier;
    @NonNull
    Long phone;
    @NonNull
    String email;

    @OneToMany(mappedBy = "supplier" , cascade = {CascadeType.REMOVE , CascadeType.PERSIST,CascadeType.MERGE})
    Set<Adress> adresses;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    Set<Offer> offers;
}
