package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> { }
