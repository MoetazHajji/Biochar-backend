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
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name_supplier;
    Long phone;
    String email;

    @OneToMany(mappedBy = "supplier")
    List<Adress> adresses;

    @ManyToMany(mappedBy = "suppliers")
    List<Product> products;
}
