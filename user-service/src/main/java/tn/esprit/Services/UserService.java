package tn.esprit.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.Roles;
import tn.esprit.Entitys.User;
import tn.esprit.Repositorys.UserRepository;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("User")
public class UserService  implements IGenericCRUD<User> {
    private UserRepository userRepository;
    @Autowired // Methode 2
    public UserService(UserRepository userRepository){  this.userRepository = userRepository; }

    @Override
    public List<User> SelectAll() {  return userRepository.findAll();   }

    @Override
    public  ResponseEntity<User>  SelectBy(long id) {
        User user = userRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(user);
    }

    @Override
    public User Insert(User object) {
        return userRepository.save(object);
    }

    @Override
    @Transactional
    public ResponseEntity<User> update(User object) {
        User user = userRepository.findById(object.getId()).orElse(null)  ;
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setEnabled(object.isEnabled());
        user.setRoles(object.getRoles());
        user.setPermissions(object.getPermissions());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        User user  = userRepository.findById(id).orElse(null) ;
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
