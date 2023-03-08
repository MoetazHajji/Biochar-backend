package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.External.Profile;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile,Long> {

}
