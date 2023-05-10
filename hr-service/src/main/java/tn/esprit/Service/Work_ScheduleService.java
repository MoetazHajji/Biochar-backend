package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.CalenderGroupWSDto;
import tn.esprit.Dto.CalenderWSDto;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Entity.ExternelEntity.Account;
import tn.esprit.Entity.ExternelEntity.Roles;
import tn.esprit.Entity.ExternelEntity.Team;
import tn.esprit.Entity.Shift;
import tn.esprit.Entity.Work_Schedule;
import tn.esprit.Interface.IWork_ScheduleService;
import tn.esprit.Mapper.Work_ScheduleMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.Work_ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Work_ScheduleService implements IWork_ScheduleService {

    private final Work_ScheduleRepository work_scheduleRepository;
    private final AccountRepository accountRepository;

    @Override
    public Work_ScheduleDto addWork_Schedule(Work_ScheduleDto workSchedule) {
        Work_Schedule ws = work_scheduleRepository.save(Work_ScheduleMapper.mapWorkScheduleToEntity(workSchedule));
        return Work_ScheduleMapper.mapWorkScheduleToDto(ws);
    }

    @Override
    public Work_ScheduleDto updateWork_Schedule(Work_ScheduleDto workSchedule) {
        Work_Schedule ws = work_scheduleRepository.save(Work_ScheduleMapper.mapWorkScheduleToEntity(workSchedule));
        return Work_ScheduleMapper.mapWorkScheduleToDto(ws);
    }

    @Override
    public void deleteWork_Schedule(Long idWS) {
        work_scheduleRepository.deleteById(idWS);
    }

    @Override
    public List<CalenderGroupWSDto> retrieveAllWork_Schedule() {
        //List<Work_Schedule> workSchedules =  work_scheduleRepository.findWSByGroup();
        //return workSchedules.stream().map(Work_ScheduleMapper::mapCalenderWorkScheduleToDto).collect(Collectors.toList());
        List<Work_Schedule> workSchedules =  work_scheduleRepository.findAll();
        return  Work_ScheduleMapper.mapCalenderGroupWorkScheduleToDto(workSchedules);
    }

    @Override
    public List<CalenderWSDto> retrieveWork_ScheduleByAccount(Long idA) {
        List<Work_Schedule> workSchedules =  work_scheduleRepository.findWork_SchedulesByAccount_Id(idA);
        return workSchedules.stream().map(Work_ScheduleMapper::mapCalenderWorkScheduleToDto).collect(Collectors.toList());
    }

    @Override
    public Work_ScheduleDto retrieveWork_ScheduleById(Long idWS) {
        Work_Schedule ws = work_scheduleRepository.findById(idWS).orElse(null);
        return Work_ScheduleMapper.mapWorkScheduleToDto(ws);
    }

    @Override
    public Work_ScheduleDto AddAndAssignWorkScheduleToAccount(Work_ScheduleDto ws, Long idAccount) {
        Work_Schedule workSchedule = Work_ScheduleMapper.mapWorkScheduleToEntity(ws);
        workSchedule = work_scheduleRepository.save(workSchedule);
        Account a = accountRepository.findById(idAccount).orElse(null);
        workSchedule.setAccount(a);
        work_scheduleRepository.save(workSchedule);
        return Work_ScheduleMapper.mapWorkScheduleToDto(workSchedule);
    }

    @Override
    public Work_ScheduleDto assignWsToAcc(Long idWs, Long idA) {
        Work_Schedule workSchedule = work_scheduleRepository.findById(idWs).orElse(null);
        Account account = accountRepository.findById(idA).orElse(null);
        workSchedule.setAccount(account);
        work_scheduleRepository.save(workSchedule);
        return Work_ScheduleMapper.mapWorkScheduleToDto(workSchedule);
    }

    @Override
    public List<Work_ScheduleDto> retrieveWorkScheduleByDate(LocalDate date) {
        List<Work_Schedule> workSchedules = work_scheduleRepository.findWorkSchedulesByWorkDate(date);
        return workSchedules.stream().map(Work_ScheduleMapper::mapWorkScheduleToDto).collect(Collectors.toList());
    }

    @Override
    public List<Work_ScheduleDto> retrieveWorkScheduleByPeriod(LocalDate startDate, LocalDate endDate) {
        List<Work_Schedule> workSchedules = work_scheduleRepository.findWorkSchedulesByDateWorkPeriod(startDate, endDate);
        return workSchedules.stream().map(Work_ScheduleMapper::mapWorkScheduleToDto).collect(Collectors.toList());
    }

    @Override
    public void createDailyWorkSchedule(LocalDate startDate, LocalDate endDate) {
        List<Account> morningShiftEmployees = accountRepository.findAccountsByShift(  Shift.Morning );
        List<Account> afternoonShiftEmployees = accountRepository.findAccountsByShift(Shift.Afternoon);
        List<Account> nightShiftEmployees = accountRepository.findAccountsByShift(Shift.Night);

        while (!startDate.isAfter(endDate)) {
            for (Account account : morningShiftEmployees) {
                work_scheduleRepository.save(new Work_Schedule(startDate,
                        LocalTime.of(07, 00),
                        LocalTime.of(13, 00), Shift.Morning, account));
            }
            for (Account account : afternoonShiftEmployees) {
                work_scheduleRepository.save(new Work_Schedule(startDate,
                        LocalTime.of(13,00),
                        LocalTime.of(19,00),Shift.Afternoon, account));
            }
            boolean workedLastNight =
                    (work_scheduleRepository.isWorking(startDate.plusDays(-1), Shift.Night) == 0 &&
                            work_scheduleRepository.isWorking(startDate.plusDays(-1), Shift.Night_Guard) == 0);

            for (Account account : nightShiftEmployees) {
                if (workedLastNight) {
                    if (account.getTeam() == Team.Team_A) {
                        work_scheduleRepository.save(new Work_Schedule(startDate,
                                LocalTime.of(19, 00),
                                LocalTime.of(07, 00), Shift.Night, account));
                    }
                }
                if ((!workedLastNight)&&(!work_scheduleRepository.isVerifyByTeam(account.getTeam(), startDate.plusDays(-1))))
                {
                    work_scheduleRepository.save(new Work_Schedule(startDate,
                            LocalTime.of(19, 00),
                            LocalTime.of(07, 00), Shift.Night, account));
                }
            }
            startDate = startDate.plusDays(1);
        }
    }

    @Override
    public void createWeekendWorkSchedule(LocalDate sunday) {
        List<Account> nightShiftWorkers = accountRepository.findAccountsByShift(Shift.Night);
        List<Account> bioDayShiftWorkers =  accountRepository.findDayShiftAccounts(Roles.Biologist);
        List<Account> recepDayShiftWorkers =  accountRepository.findDayShiftAccounts(Roles.Receptionist);

        boolean isSunday = sunday.get(ChronoField.DAY_OF_WEEK) == 7;
        boolean bioHasShifts = work_scheduleRepository.hasShifts(Roles.Biologist) == 0;
        boolean recepHasShifts = work_scheduleRepository.hasShifts(Roles.Receptionist) == 0;

        if (bioHasShifts){
            work_scheduleRepository.save(new Work_Schedule(sunday,
                    LocalTime.of(07, 00),
                    LocalTime.of(19, 00), Shift.Day_Guard, bioDayShiftWorkers.get(0)));
            work_scheduleRepository.save(new Work_Schedule(sunday,
                    LocalTime.of(07, 00),
                    LocalTime.of(19, 00), Shift.Day_Guard, bioDayShiftWorkers.get(1)));
        }else {
            for (Account account : bioDayShiftWorkers){
                if (isSunday && work_scheduleRepository.AccounIsWorking(sunday.plusDays(-7), account.getId())==0
                        && work_scheduleRepository.AccounIsWorking(sunday.plusDays(-14), account.getId())==0){
                    work_scheduleRepository.save(new Work_Schedule(sunday,
                            LocalTime.of(07, 00),
                            LocalTime.of(19, 00), Shift.Day_Guard, account));
                }
            }}

        if (recepHasShifts){
            work_scheduleRepository.save(new Work_Schedule(sunday,
                    LocalTime.of(07, 00),
                    LocalTime.of(19, 00), Shift.Day_Guard, recepDayShiftWorkers.get(0)));
        }else {
            for (Account account : recepDayShiftWorkers){
                if (isSunday && work_scheduleRepository.AccounIsWorking(sunday.plusDays(-7), account.getId())==0
                        && work_scheduleRepository.AccounIsWorking(sunday.plusDays(-14), account.getId())==0
                        && work_scheduleRepository.AccounIsWorking(sunday.plusDays(-21), account.getId())==0){
                    work_scheduleRepository.save(new Work_Schedule(sunday,
                            LocalTime.of(07, 00),
                            LocalTime.of(19, 00), Shift.Day_Guard, account));
                }
            }}

        boolean workedLastNight =
                (work_scheduleRepository.isWorking(sunday.plusDays(-1), Shift.Night) == 0 &&
                        work_scheduleRepository.isWorking(sunday.plusDays(-1), Shift.Night_Guard) == 0);

        for (Account account : nightShiftWorkers) {
            if (workedLastNight) {
                if (account.getTeam() == Team.Team_A) {
                    work_scheduleRepository.save(new Work_Schedule(sunday,
                            LocalTime.of(19, 00),
                            LocalTime.of(07, 00), Shift.Night_Guard, account));
                }
            }
            if ((!workedLastNight)&&(!work_scheduleRepository.isVerifyByTeam(account.getTeam(), sunday.plusDays(-1))))
            {
                work_scheduleRepository.save(new Work_Schedule(sunday,
                        LocalTime.of(19, 00),
                        LocalTime.of(07, 00), Shift.Night_Guard, account));
            }
        }
    }

    @Override
    public void deleteWorkScheduleByDate(LocalDate dateWork) {
        work_scheduleRepository.deleteWork_SchedulesByDate(dateWork);
    }

    @Override
    public void deleteWorkScheduleByPeriod(LocalDate startDate, LocalDate endDate) {
        work_scheduleRepository.deleteWork_SchedulesByDateWorkBetween(startDate, endDate);
    }
}
