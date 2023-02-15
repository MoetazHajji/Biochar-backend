package tn.esprit.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.Entity.Detail;

public interface RepoDetail extends CrudRepository<Detail,Integer> {
}
