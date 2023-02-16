package tn.esprit.Entitys;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name ="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;

    @Column(name = "username" )
    String username;

    @Column(name = "password" )
    String password;

    @Column(name = "roles" )
    @Enumerated(EnumType.STRING)
    Roles roles;

    @Column(name = "permissions" )
    String permissions;

    @Column(name = "is_enabled" )
    boolean isEnabled;

    @OneToOne(mappedBy = "user")
    Account account;


    public User(String username, String password) {  this. username = username; this.password = password;  }
}
