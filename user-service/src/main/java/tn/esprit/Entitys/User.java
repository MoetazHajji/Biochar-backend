package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

@Entity
@Builder
@Table(name ="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails, Serializable {
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

    @Column(name = "code" )
    String code;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;


    @JsonIgnore
    @OneToOne(mappedBy = "user" ,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Account account;


    public User(String username, String password) {  this. username = username; this.password = password;  }
    public User(String username, String password, Roles roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }
    public User(String username, String password, Roles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public User(String username, String password, Account account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

   /* public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }*/











    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        //   this.user.getPermissionList().forEach(p -> {
        //       GrantedAuthority authority = new SimpleGrantedAuthority(p);
        //       authorities.add(authority);
        //   });

        // Extract list of roles (ROLE_name)
        //  this.user.getRoleList().forEach(r -> {
        //      GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
        //      authorities.add(authority);
        //  });
        GrantedAuthority  authority = new SimpleGrantedAuthority( this.roles.name());
        authorities.add(authority);
     /*  if (   this.permissions != null ){
           authority = new SimpleGrantedAuthority(this.permissions);
        authorities.add(authority);}*/
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
