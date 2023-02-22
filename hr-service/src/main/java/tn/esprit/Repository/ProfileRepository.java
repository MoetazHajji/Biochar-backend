package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}