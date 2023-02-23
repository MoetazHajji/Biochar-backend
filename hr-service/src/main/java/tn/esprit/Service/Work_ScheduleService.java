package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Entity.Account;
import tn.esprit.Entity.Work_Schedule;
import tn.esprit.Interface.IWork_ScheduleService;
import tn.esprit.Mapper.Work_ScheduleMapper;
import tn.esprit.Repository.AccountRepository;
import tn.esprit.Repository.Work_ScheduleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<Work_ScheduleDto> retrieveAllWork_Schedule() {
        List<Work_Schedule> workSchedules = (List<Work_Schedule>) work_scheduleRepository.findAll();
        return workSchedules.stream().map(Work_ScheduleMapper::mapWorkScheduleToDto).collect(Collectors.toList());
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
        if (workSchedule.getAccounts()==null){
            Set<Account> accounts =new HashSet<>();
            accounts.add(a);
            workSchedule.setAccounts(accounts);
        }else {
            workSchedule.getAccounts().add(a);
        }
        work_scheduleRepository.save(workSchedule);
        return Work_ScheduleMapper.mapWorkScheduleToDto(workSchedule);
    }

    @Override
    public Work_ScheduleDto assignWsToAcc(Long idWs, Long idA) {
        Work_Schedule workSchedule = work_scheduleRepository.findById(idWs).orElse(null);
        Account account = accountRepository.findById(idA).orElse(null);

        if (workSchedule.getAccounts()==null){
            Set<Account> accounts =new HashSet<>();
            accounts.add(account);
            workSchedule.setAccounts(accounts);

        }else {
            workSchedule.getAccounts().add(account);
        }
        work_scheduleRepository.save(workSchedule);
        return Work_ScheduleMapper.mapWorkScheduleToDto(workSchedule);
    }
}
