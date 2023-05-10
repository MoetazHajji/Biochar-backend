package tn.esprit.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Configures.MyConfigInitParameters;
import tn.esprit.Entitys.*;
import tn.esprit.Models.AuthenticationRequest;
import tn.esprit.Models.AuthenticationResponse;
import tn.esprit.Entitys.Msg;
//import tn.esprit.Models.RegisterRequest;
import tn.esprit.Models.AuthenticationStatus;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.TokenRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final AccountRepository accountRepository;
  private final IFileService ifileService;
  private final IAttachmentService iAttachmentService;
  private final   IFileService iFileService;


  private final KafkaTemplate<Object,  Msg > kafkaTemplate;

  public AuthenticationResponse register(AuthenticationRequest request) {
    //if (  userRepository. isCorrectEmail( request.getEmail()   ))
    //{ return AuthenticationResponse.builder().message("other mail found").build(); }
    if( userRepository. isCorrectUserName( request.getUsername()  ))
    {   return AuthenticationResponse.builder()
            .status(AuthenticationStatus.UNSUCCESSFUL)
            .message("other username found").build();}
    String  code = this.getRandomNumber( 100 , 999 ) +"-"+this.getRandomNumber( 100 , 999 ) +"-"+this.getRandomNumber(  100 , 999) +"-"+this.getRandomNumber(  100 , 999  );
    User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(true) //false
                    .code(code)
                    .roles(Roles.Patient)
                    .build();
    Account account = Account.builder().createdAt(LocalDateTime.now()).email(request.getEmail()).user(user).build();
    account.setUser(user);
    user.setAccount(account);


    try {
      MultipartFile multipartFile = ifileService.importFileToMultipartFile(FileService.defaultUserPhoto);
      Attachment attachment  = iAttachmentService.saveAttachment(multipartFile);
      account.setAttachment(attachment);   }
    catch (IOException e) {   System.out.println("IOException: "+e.getMessage());  }
    catch (Exception e) {     System.out.println("Exception: "+e.getMessage());    }


    account =  accountRepository.save(account);
    try {
    Msg msg = new Msg();
    msg.setSubject("Confirmation Address Mail");
    msg.setEmail(account.getEmail());
    msg.getBodyContents().add(new BodyContent("text/plain","body"));
    String file =    iFileService.Edit_ConfirmMailPage(user.getUsername(),
            MyConfigInitParameters.staticLinkServiceUser +"/user/auth/confirm_email/"+code);
    msg.getBodyContents().add(new BodyContent("text/html",  file));
    kafkaTemplate.send("topic-service-mail-sender-send-mail",msg);
    kafkaTemplate.flush();
    }
    catch (Exception e )
    {

      return AuthenticationResponse.builder().
              status(AuthenticationStatus.ERROR).
              message(e.getMessage()).build();
    }
    return AuthenticationResponse.builder() .
            status(AuthenticationStatus.SUCCESSFUL).
            message("To complete next step you should verify confirmation mail").build();
  }




  public AuthenticationResponse  confirmEmail(String code)
  {
   User user =  userRepository.findUserByCode( code );
   if ( user != null ){
       user.setEnabled(true);
       user.setCode("");
       userRepository.save(user);
       return AuthenticationResponse.builder() .
               status(AuthenticationStatus.SUCCESSFUL).
              message("you can login authentication").build();
   }
    return AuthenticationResponse.builder().status(AuthenticationStatus.ERROR).message("Perhaps we can't find  your register").build(); }



  public AuthenticationResponse sendMailCode_ForgotPassword(String email) {
    String code = null ;
    if ( userRepository.isCorrectEmail(email)){
      code =  this.getRandomNumber( 100 , 999 ) +"-"+
              this.getRandomNumber( 100 , 999 ) +"-"+
              this.getRandomNumber(  100 , 999) +"-"+
              this.getRandomNumber(  100 , 999  );
      User user = userRepository.findUserByEmail(  email   );
      user.setCode(code);
     userRepository.save(user);
      try {
        Msg msg = new Msg();
        msg.setSubject("Forgot Password");
        msg.setEmail(email);
        msg.getBodyContents().add(new BodyContent("text/plain","body"));
        String file =  iFileService.Edit_forgotPasswordPage(user.getUsername(),
                code );
        //MyConfigInitParameters.staticLinkServiceUser +"/user/auth/confirmationCode/"+code
        msg.getBodyContents().add(new BodyContent("text/html",  file));
        kafkaTemplate.send("topic-service-mail-sender-send-mail",msg);
        kafkaTemplate.flush();
      }
      catch (Exception e )
      {
        return AuthenticationResponse.builder() .
                status(AuthenticationStatus.ERROR).
                message("Some thing wrong").build();}
      }
    else {
      return AuthenticationResponse.builder() .
            status(AuthenticationStatus.ERROR).
            message("Your mail not correct").build();}
    return AuthenticationResponse.builder() .
            status(AuthenticationStatus.SUCCESSFUL).
            message("Your mail is correct so you see your email for complete next step").build();
  }
  @Transactional
  public AuthenticationResponse confirmationCode(String code, String password) {
    if (   userRepository.isCorrectCode( code ) && !password.isEmpty()) {
      User user= userRepository.findUserByCode( code);
      user.setPassword(   passwordEncoder.encode(password)   );
      user.setCode("");
      userRepository.save(user);
      return AuthenticationResponse.builder() .
              status(AuthenticationStatus.SUCCESSFUL).
              message("Your code is correct and we change password").build();}
    else { return AuthenticationResponse.builder() .
                                        status(AuthenticationStatus.ERROR).
                                        message("Your code is not correct ").build();}
  }
  @Transactional
  public AuthenticationResponse updatePassword(AuthenticationRequest userRequest, String newPassword) {
    System.out.println(userRequest.getUsername());    System.out.println(userRequest.getPassword());
    System.out.println(newPassword);
    if( newPassword.isEmpty() )
    {   return AuthenticationResponse.builder()
            .status(AuthenticationStatus.ERROR)
            .message("New Password empty").build();}
    if( !userRepository. isCorrectUserName( userRequest.getUsername()  ))
    {   return AuthenticationResponse.builder().status(AuthenticationStatus.UNSUCCESSFUL)
            .message("Cannot found username verify you enter correct").build();}

    User user = userRepository.findUserByUsername( userRequest.getUsername() ).get();
    boolean matches = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());
    if( !matches ){   return AuthenticationResponse.builder().status(AuthenticationStatus.UNSUCCESSFUL)
            .message("Verify your current password").build();}

    user.setPassword(passwordEncoder.encode(newPassword));
    //userRepository.save(user);
    return AuthenticationResponse.builder(). status(AuthenticationStatus.SUCCESSFUL).
            message("Successful update password").build();
  }
  @Transactional
  public AuthenticationResponse updateRoleAndActivate(String username , Roles role, boolean enabled) {
    System.out.println(username);    System.out.println(role);
    System.out.println(enabled);
    if( !userRepository. isCorrectUserName( username ))
    {   return AuthenticationResponse.builder().status(AuthenticationStatus.UNSUCCESSFUL)
            .message("Cannot found username verify you enter correct").build();}

    User user = userRepository.findUserByUsername( username ).get();

    user.setRoles(role);
    user.setEnabled(enabled);
    //userRepository.save(user);
    return AuthenticationResponse.builder(). status(AuthenticationStatus.SUCCESSFUL).
            message("Successful update role and state enable user").build();
  }


  public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    User user = userRepository.findUserByUsername(request.getUsername())
            .orElseThrow(() -> new Exception("Error findByEmail"));
    String jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
         .status(AuthenticationStatus.SUCCESSFUL)
         .message("Successful to access account")
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}













  /*public AuthenticationResponse register_v2(RegisterRequest request) {
    User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(Roles.Patient)
                    .build();
    user.setEnabled(false);
    User savedUser = userRepository.save(user);
    Account account = Account.builder().email(request.getEmail()).user(savedUser).build();
    accountRepository.save(account);
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }*/