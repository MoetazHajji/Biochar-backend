package tn.esprit.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.*;
import tn.esprit.Mappers.UserMapper;
import tn.esprit.Entitys.Msg;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.exception.RessourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor



@Service("User")
public class UserService  implements IUserService {
    private UserRepository userRepository;
    //private PasswordEncoder passwordEncoder;



    @Autowired // Methode 2
    public UserService(UserRepository userRepository
                       //,PasswordEncoder passwordEncoder,
                      )
    {
        this.userRepository = userRepository;
        //this.passwordEncoder =  passwordEncoder;
    }

    @Override
    public List<UserDto> SelectAll() {
        return userRepository.findAll().stream().map(user -> UserMapper.mapToDto(user)).collect(Collectors.toList());   }

    @Override
    public  UserDto  SelectBy(long id) {
        User user = userRepository.findById(id).
                orElseThrow(()-> new RessourceNotFoundException("Service User : selectBy Utilisateur not existe with id : "+id));
        return UserMapper.mapToDto(user) ;
    }

    @Override
    public UserDto Insert(UserDto object) {
        object.setEnabled(true);
        // object.setPassword( passwordEncoder.encode(  object.getPassword()));
        //object.setRoles(Roles.Unknown);
        //object.setPermissions("");
        User user = UserMapper.mapToEntity(object);
        return UserMapper.mapToDto( userRepository.save(  user  ));
    }

    @Override
    @Transactional
    public  UserDto  update(UserDto object) {
        User user = userRepository.findById(object.getId()).orElse(null)  ;
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setEnabled(object.isEnabled());
        user.setRoles(object.getRoles());
        user.setPermissions(object.getPermissions());
        user = userRepository.save(user);
        return  UserMapper.mapToDto( user )  ;
    }
    @Override
    @Transactional
    public  UserDto  updateRole(long id_user,Roles role) {
        User user = userRepository.findById(id_user).
                orElseThrow(()-> new RessourceNotFoundException("Service User : updateRole Utilisateur not existe with id : "+id_user))  ;


        user.setRoles(role);
        user = userRepository.save(user);
        return  UserMapper.mapToDto( user )  ;
    }

    @Override
    public boolean delete(long id) {
        boolean deleted = false;
        User user  = userRepository.findById(id). orElseThrow(()-> new RessourceNotFoundException("Service User : delete Utilisateur not existe with id : "+id)) ;
        if (user != null ) {
            userRepository.delete(user);
            deleted = true;
        }
        return deleted;
    }
    @Override
    public ResponseEntity<HttpStatus> deleteAll( ) {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Transactional
    @Override
    public String sendMailCode_ForgotPassword(String email) {
        String code = null ;
        if ( userRepository.isCorrectEmail(email)){
            code = this.getRandomNumber( 100 , 999 ) +"-"+
                    this.getRandomNumber( 100 , 999 ) +"-"+
                    this.getRandomNumber(  100 , 999) +"-"+
                    this.getRandomNumber(  100 , 999  );
            User user = userRepository.findUserByEmail(  email   );
            user.setPassword(code);
            Msg msg = new Msg("Forgot Password",email,"your code verification : "+code);
            //IEmailSenderService.SendSimpleEmail(msg);
            userRepository.save(user);
            return code;}
        else { return  code;}
    }
    @Transactional
    @Override
    public boolean confirmationCode(String code, String password) {
        System.out.println(userRepository.isCorrectPassword( code ));
        if (   userRepository.isCorrectPassword( code )) {
            User user= userRepository.findUserByPassword( code);
            user.setPassword(   password   );
            userRepository.save(user);
            return true;}
        else {return false;}
    }
    public boolean updatePassword(String oldpassword, String password) {
        System.out.println(userRepository.isCorrectPassword( oldpassword ));
        if (   userRepository.isCorrectPassword( oldpassword )) {
            User user= userRepository.findUserByPassword( oldpassword);
            user.setPassword(   password   );
            userRepository.save(user);
            return true;}
        else {return false;}
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
