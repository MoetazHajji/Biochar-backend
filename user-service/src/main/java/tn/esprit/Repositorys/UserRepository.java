package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
