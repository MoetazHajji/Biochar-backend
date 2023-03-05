package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import tn.esprit.Entity.Cookie;

import java.math.BigInteger;
import java.util.Date;

import java.util.Optional;


public interface CookiesRepository extends JpaRepository<Cookie, BigInteger> {

    @Modifying
    void deleteByDateBefore(Date expiryDate);

    Optional<Cookie> findFirstByUserAndDomainAndPath(int user, String domain, String path);
}
