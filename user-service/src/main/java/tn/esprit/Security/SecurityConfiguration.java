package tn.esprit.Security;

import com.google.common.collect.ImmutableList;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tn.esprit.Entitys.Roles;

import java.util.Arrays;
import java.util.Collections;

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
        // .csrf().disable() //    correct    $$$$$$$$$$$$$
         .cors().and().csrf().disable()
            .authorizeRequests()
            .anyRequest().permitAll()


//            .authorizeHttpRequests()
//        .antMatchers("/user/auth/**").permitAll()
//             .antMatchers("/account/**").authenticated()
//            .antMatchers("/Appointment/**").permitAll()
//            .antMatchers("/account/**").permitAll()
//            .antMatchers("/timeoff/**").permitAll()
//            .antMatchers("/timeoff/add").permitAll()
            // .antMatchers("/Appointment/**").hasAuthority(Roles.Patient.name())
            //  .antMatchers("/Appointment/**").hasAuthority(Roles.Receptionist.name())
            //.antMatchers("/TimeOff/**").hasAuthority(Roles.Chief_Service.name())


            //  .antMatchers("/biochar/stock-service/**").hasAuthority(Roles.Chief_Service.name())
            //   .antMatchers("/biochar/internship/**").hasAuthority(Roles.Intern.name())
          // .antMatchers("/User/**").permitAll()
            // .anyRequest().authenticated()


           // .antMatchers("/api/v1/demo-controller").authenticated()


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


//  @Bean
//  public CorsConfigurationSource corsConfigurationSource() {
//    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.addAllowedOrigin("http://localhost:4200");
//    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//    //configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//    //configuration.setExposedHeaders(Arrays.asList("Authorization"));
//    configuration.setAllowCredentials(true);
//
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//
//    return source;
//  }


}
