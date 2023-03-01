package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.External.Profile;

public interface ProfileRepository extends CrudRepository<Profile,Integer> {
}
