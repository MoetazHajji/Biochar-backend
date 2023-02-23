package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String Username);
    @Query("select u from User u JOIN u.account ac  where  ((ac.email = :email)) ")
     User findUserByEmail(@Param("email") String email   );
    @Query("select case when (count(u) > 0)  then true else false end from User u JOIN u.account ac  where  ((ac.email = :email))")
    boolean isCorrectEmail(@Param("email") String email   );

    @Query("select case when (count(u) > 0)  then true else false end from User u where (u.password = :password)")
    boolean isCorrectPassword(@Param("password") String password   );
    User findUserByPassword(String password);


}
