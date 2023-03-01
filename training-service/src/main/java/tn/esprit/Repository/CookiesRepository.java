package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.data.mongodb.repository.Query;
import tn.esprit.Entity.Cookie;

import java.math.BigInteger;
import java.util.List;


public interface CookiesRepository extends JpaRepository<Cookie, BigInteger> {


}
