package tn.esprit.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true,nullable = false,length = 20 )
    String name_product;

    @Column(unique = true,nullable = false,length = 25 )
    String reference;

    //@Column(unique = false,nullable = false)
    Double price;

    Double size_product;

    // @Column(unique = true,nullable = false,length = 100 )
    String description;

    //@Column(unique = true,nullable = false,length = 200 )
    String image;

    Long count_order;

    //@Column(unique = true,nullable = false,length = 200 )
    @Min(0)
    Double quantity;

    @Min(0)
    Double autoFillQuantity;

    @Min(0)
    Double autoFillThreshold;



    @NonNull
    @Enumerated(EnumType.STRING)
    Type_product type_product;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    Set<Stock> stocks;


    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Offer> offers;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<CommandLigne> commandLignes;
}
