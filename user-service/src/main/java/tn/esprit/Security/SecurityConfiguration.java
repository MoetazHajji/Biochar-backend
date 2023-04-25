package tn.esprit.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import tn.esprit.Entitys.Roles;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
        .antMatchers("/user/auth/**").permitAll()
        .antMatchers("/Account/**").permitAll()
        //.antMatchers("/Account/**").authenticated()
        .antMatchers("/Appointment/**").hasAuthority(Roles.Patient.name())
        .antMatchers("/Appointment/**").hasAuthority(Roles.Receptionist.name())


            .antMatchers("/Account/**").permitAll()


       // .antMatchers("/Appointment/**").permitAll()
       .antMatchers("/User/**").permitAll()
       // .anyRequest().authenticated()


            .antMatchers("/api/v1/demo-controller").authenticated()

            //.antMatchers("/api/v1/User-Conroller").hasAuthority("ADMIN")
            .antMatchers("/Page/management-patient/**").hasAuthority(Roles.Patient.name())
            .antMatchers("/Page/management-users/**").hasAuthority(Roles.Chief_Service.name())
            .antMatchers("/Page/management-users/**").hasAuthority(Roles.Chief_Service.name())
           // .antMatchers("/Page/management-patient/**").hasRole("ROLE_"+Roles.Patient.name())
           // .antMatchers("/Page/management-users/**").hasRole(Roles.Chief_Service.name())
           // .antMatchers("/api/v1/User-Conroller/*").hasRole("ADMIN")


        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/user/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
