package tn.esprit.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entitys.*;
import tn.esprit.Models.AuthenticationRequest;
import tn.esprit.Models.AuthenticationResponse;
import tn.esprit.Models.Msg;
import tn.esprit.Models.RegisterRequest;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.TokenRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

  private final   IEmailSenderService iEmailSenderService;

  private final   IFileService iFileService;
  public AuthenticationResponse register(RegisterRequest request) {
    if (  userRepository. isCorrectEmail( request.getEmail()   ))
    {
      return AuthenticationResponse.builder().message("other mail found").build();
    }
    if( userRepository. isCorrectUserName( request.getUsername()  ))
    {
      return AuthenticationResponse.builder().message("other username found").build();
    }
    String  code = this.getRandomNumber( 100 , 999 ) +"-"+
            this.getRandomNumber( 100 , 999 ) +"-"+
            this.getRandomNumber(  100 , 999) +"-"+
            this.getRandomNumber(  100 , 999  );
    User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(false)
                    .code(code)
                    .roles(Roles.Patient)
                    .build();
    User savedUser = userRepository.save(user);
    Account account = Account.builder()
                             .email(request.getEmail())
                             .user(savedUser).build();
    accountRepository.save(account);
    try {
      Msg msg = new Msg("Forget Password",
                         "belhsenbachouch97@gmail.com",
                              iFileService.Edit_ConfirmMailPage(user.getUsername(),
                                                        "http://localhost:8099/biochar/user-service/user/auth/confirm_email/"+code,
                                                "http://locahost:8099/biochar/"));

      iEmailSenderService.SendEmailWithHtml(msg);
    }
    catch (Exception e )
    {
      return AuthenticationResponse.builder().
              type("ERROR").
              message(e.getMessage()).build();
    }
    return AuthenticationResponse.builder() .
            type("SUCCESSUF").
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
              type("SUCCESSUF").
              message("you can login authentication").build();
   }
    return AuthenticationResponse.builder() .
            type("ERROR").
            message("Perhaps we can't find  your register").build();
  }
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


        Msg msg = new Msg("Forgot Password",
                email,
                iFileService.Edit_forgotPasswordPage("belhsen",
                        code,
                        "http://localhost:8099/biochar/user-service/user/auth/confirmationCode/"+code,"http://locahost:8099/biochar/")
        );
        iEmailSenderService.SendEmailWithHtml(msg);

      }
      catch (Exception e )
      {
        return AuthenticationResponse.builder() .
                type("ERROR").
                message("Some thing wrong").build();}
      }
    else {
      return AuthenticationResponse.builder() .
            type("ERROR").
            message("Your mail not correct").build();}
    return AuthenticationResponse.builder() .
            type("SUCCESSUF").
            message("Your mail is correct so you see your email for complete next step").build();
  }
  @Transactional
  public AuthenticationResponse confirmationCode(String code, String password) {
    if (   userRepository.isCorrectCode( code )) {
      User user= userRepository.findUserByCode( code);
      user.setPassword(   passwordEncoder.encode(password)   );
      user.setCode("");
      userRepository.save(user);
      return AuthenticationResponse.builder() .
              type("SUCCESSUF").
              message("Your code is correct and we change password").build();}
    else { return AuthenticationResponse.builder() .
                                        type("ERROR").
                                        message("Your code is not correct ").build();}
  }









  public AuthenticationResponse register_v2(RegisterRequest request) {
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
