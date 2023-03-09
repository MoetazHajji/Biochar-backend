package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.User;

import java.time.LocalDate;
import java.time.LocalTime;
@Repository
public interface TimeOffRepository extends JpaRepository<TimeOff, Long> {
    @Query("SELECT CASE WHEN COUNT(timeoff) > 0 THEN true ELSE false END FROM TimeOff timeoff WHERE (timeoff.startDate <= :date AND timeoff.endDate >= :date AND (timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime OR timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.Date) )" )
    boolean isInThatDate(@Param("date") LocalDate date );



    @Query("SELECT CASE WHEN COUNT(timeoff) > 0 THEN true ELSE false END FROM TimeOff timeoff WHERE   (((timeoff.startTime >= :startime) AND (timeoff.startTime < :endtime)) OR ((timeoff.endTime > :startime) AND  (timeoff.endTime <= :endtime) ) OR ((timeoff.startTime <= :startime) AND (timeoff.endTime >= :endtime) )  OR ((timeoff.startTime >= :startime) AND (timeoff.endTime <= :endtime))     ) AND  ((timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime) OR (timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime))" )
    boolean isInBetweenTwoTime(@Param("startime") LocalTime startime, @Param("endtime") LocalTime endtime );
}
