package tn.esprit.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Models.AuthenticationRequest;
import tn.esprit.Models.AuthenticationResponse;
import tn.esprit.Models.RegisterRequest;
import tn.esprit.Services.AuthenticationService;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @GetMapping("/confirm_email/{code}")
    public ResponseEntity<AuthenticationResponse> confirmEmail(@PathVariable("code")  String code) {
        return ResponseEntity.ok(service.confirmEmail(code));
    }

    @PutMapping("sendMailCode_ForgotPassword/{email}")
    ResponseEntity<AuthenticationResponse>  sendMailCode_ForgotPassword(@PathVariable("email")  String email){
        return  ResponseEntity.ok(service.sendMailCode_ForgotPassword( email ));}


   @PutMapping("confirmationCode/{code}/{password}")
   ResponseEntity<AuthenticationResponse>  confirmationCode(@PathVariable("code") String code,@PathVariable("password")  String password){
        return  ResponseEntity.ok( service.confirmationCode( code,  password));
    }

 @PostMapping("/authenticate")
 public ResponseEntity<AuthenticationResponse> authenticate(
         @RequestBody AuthenticationRequest request
 ) throws Exception {
   return ResponseEntity.ok(service.authenticate(request));
 }


}
