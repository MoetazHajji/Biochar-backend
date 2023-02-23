package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.User;

import java.util.Date;
import java.util.List;

public interface TimeOffRepository extends JpaRepository<TimeOff, Long> {
    @Query("SELECT CASE WHEN COUNT(timeoff) > 0 THEN true ELSE false END FROM TimeOff timeoff WHERE (timeoff.start_date <= :date AND timeoff.end_date >= :date AND (timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime OR timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.Date) )" )
    boolean isInThatDate(@Param("date") Date date );



    @Query("SELECT CASE WHEN COUNT(timeoff) > 0 THEN true ELSE false END FROM TimeOff timeoff WHERE   (((timeoff.start_time >= :startime) AND (timeoff.start_time < :endtime)) OR ((timeoff.end_time > :startime) AND  (timeoff.end_time <= :endtime) ) OR ((timeoff.start_time <= :startime) AND (timeoff.end_time >= :endtime) )  OR ((timeoff.start_time >= :startime) AND (timeoff.end_time <= :endtime))     ) AND  ((timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime) OR (timeoff.typeTimeOff = tn.esprit.Entitys.TypeTimeOff.DateTime))" )
    boolean isInBetweenTwoTime(@Param("startime") Date startime,@Param("endtime") Date endtime );
}
