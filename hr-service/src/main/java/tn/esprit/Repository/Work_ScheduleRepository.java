package tn.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.ExternelEntity.Roles;
import tn.esprit.Entity.ExternelEntity.Team;
import tn.esprit.Entity.Shift;
import tn.esprit.Entity.Work_Schedule;

import java.time.LocalDate;
import java.util.List;

public interface Work_ScheduleRepository extends JpaRepository<Work_Schedule, Long> {

    List<Work_Schedule> findWork_SchedulesByAccount_Id(Long idA);
    @Query("select ws from Work_Schedule ws group by ws.start_time")
    List<Work_Schedule> findWSByGroup();

    @Query("select ws from Work_Schedule ws where ws.dateWork= ?1")
    List<Work_Schedule> findWorkSchedulesByWorkDate(LocalDate date);
    @Query("select ws from Work_Schedule ws where ws.dateWork in (?1, ?2)")
    List<Work_Schedule> findWorkSchedulesByDateWorkPeriod(LocalDate StartDate, LocalDate EndDate);

    @Query("select count (ws) from Work_Schedule ws where ((ws.dateWork =:date_work) and (ws.shift = :shift))")
    Long isWorking(@Param("date_work") LocalDate date_work, @Param("shift") Shift shift);

    @Query("select count (ws) from Work_Schedule ws join ws.account acc where (acc.id =:id and (ws.dateWork =:date_work) and (ws.shift = tn.esprit.Entity.Shift.Day_Guard))")
    Long AccounIsWorking(@Param("date_work") LocalDate date_work, @Param("id") Long id);

    @Query("select count (ws) from Work_Schedule ws join ws.account acc where (ws.shift = tn.esprit.Entity.Shift.Day_Guard and (acc.role = :role))")
    Long hasShifts(@Param("role") Roles role);

    @Query("SELECT CASE WHEN COUNT(ws) > 0 THEN true ELSE false END FROM Work_Schedule ws WHERE (((ws.shift = tn.esprit.Entity.Shift.Night) or (ws.shift = tn.esprit.Entity.Shift.Night_Guard)) and (ws.account.team =:team) and (ws.dateWork =:date_work))")
    boolean isVerifyByTeam(@Param("team") Team team, @Param("date_work") LocalDate date_work);

    @Transactional
    @Modifying
    @Query("delete from Work_Schedule ws where ws.dateWork = ?1")
    void deleteWork_SchedulesByDate(LocalDate dateWork);
    @Transactional
    @Modifying
    @Query("delete from Work_Schedule ws where ws.dateWork in (?1, ?2)")
    void deleteWork_SchedulesByDateWorkBetween(LocalDate startDate, LocalDate endDate);

}