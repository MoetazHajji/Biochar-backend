package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Cookie;

import java.math.BigInteger;


public interface CookiesRepository extends JpaRepository<Cookie, BigInteger> {


}
