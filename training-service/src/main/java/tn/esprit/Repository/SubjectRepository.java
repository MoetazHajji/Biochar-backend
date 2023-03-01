package tn.esprit.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.Entity.Subject;

import java.math.BigInteger;
import java.util.Optional;


public interface ISubjectRepository extends MongoRepository<Subject, BigInteger> {
    Optional<Subject> findFirstByTitle(String title);
}
