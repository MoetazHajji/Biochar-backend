package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Appointment;

import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT CASE WHEN COUNT(appt) > 0 THEN true ELSE false END FROM Appointment appt WHERE (((appt.appointmentStartTime >= :startime) AND (appt.appointmentStartTime < :endtime)) OR ((appt.appointmentEndTime > :startime) AND  (appt.appointmentEndTime <= :endtime) ) OR ((appt.appointmentStartTime <= :startime) AND (appt.appointmentEndTime >= :endtime) )  OR ((appt.appointmentStartTime >= :startime) AND (appt.appointmentEndTime <= :endtime)))")
    boolean isInBetweenTwoTimeAndDate(@Param("startime") Date startime, @Param("endtime") Date endtime  );

}
