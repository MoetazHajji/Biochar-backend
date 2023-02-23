package tn.esprit.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.Profile;

@Transactional
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @Modifying
    @Query(value = "UPDATE profile SET experience=experience+1;", nativeQuery = true)
    int upgradeExperience();


}