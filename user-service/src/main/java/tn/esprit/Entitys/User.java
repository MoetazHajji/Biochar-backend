package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Builder
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
    @JsonIgnore
    @OneToOne(mappedBy = "user" ,cascade = {CascadeType.PERSIST,CascadeType.REMOVE} , fetch = FetchType.EAGER)
    Account account;


    public User(String username, String password) {  this. username = username; this.password = password;  }
    public User(String username, String password, Roles roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User(String username, String password, Account account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
