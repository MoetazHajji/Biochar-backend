package tn.esprit.Entitys;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Builder
//@Table(name ="Permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
   // @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id" )
    long id;

    //@Column(name = "type_permission" )
    //@Enumerated(EnumType.STRING)
    TypePermission typePermission;

    //@Column(name = "roles" )
    //@Enumerated(EnumType.STRING)
    Roles roles;

    //@Column(name = "path" )
    String path;
}
