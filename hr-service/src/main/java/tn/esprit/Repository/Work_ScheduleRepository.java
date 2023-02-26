package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Entity.Work_Schedule;

public interface Work_ScheduleRepository extends JpaRepository<Work_Schedule, Long> {
}