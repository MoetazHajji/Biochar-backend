package tn.esprit.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Dto.UserDto;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Appointment;
import tn.esprit.Entitys.Roles;
import tn.esprit.Entitys.User;
import tn.esprit.Mappers.UserMapper;
import tn.esprit.Models.Msg;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.UserRepository;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor



@Service("User")
public class UserService  implements IUserService {
    private UserRepository userRepository;
    private IEmailSenderService IEmailSenderService;
    //private PasswordEncoder passwordEncoder;



    @Autowired // Methode 2
    public UserService(UserRepository userRepository ,
                       //PasswordEncoder passwordEncoder,
                       @Qualifier("EmailSender") IEmailSenderService IEmailSenderService)
    {
        this.userRepository = userRepository;
        //this.passwordEncoder =  passwordEncoder;
        this.IEmailSenderService =  IEmailSenderService;
    }

    @Override
    public List<UserDto> SelectAll() {
        return userRepository.findAll().stream().map(user -> UserMapper.mapToDto(user)).collect(Collectors.toList());   }

    @Override
    public  ResponseEntity<UserDto>  SelectBy(long id) {
        User user = userRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok( UserMapper.mapToDto(user) );
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
    public ResponseEntity<UserDto> update(UserDto object) {
        User user = userRepository.findById(object.getId()).orElse(null)  ;
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setEnabled(object.isEnabled());
        user.setRoles(object.getRoles());
        user.setPermissions(object.getPermissions());
        user = userRepository.save(user);
        return ResponseEntity.ok(UserMapper.mapToDto( user ) );
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        User user  = userRepository.findById(id).orElse(null) ;
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        IEmailSenderService.SendSimpleEmail(msg);
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


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


}
