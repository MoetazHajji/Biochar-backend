package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Work_Schedule;
import tn.esprit.Interface.IWork_ScheduleService;
import tn.esprit.Repository.Work_ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Work_ScheduleService implements IWork_ScheduleService {

    private final Work_ScheduleRepository work_scheduleRepository;

    @Override
    public Work_Schedule addWork_Schedule(Work_Schedule workSchedule) {
        return work_scheduleRepository.save(workSchedule);
    }

    @Override
    public Work_Schedule updateWork_Schedule(Work_Schedule workSchedule) {
        return work_scheduleRepository.save(workSchedule);
    }

    @Override
    public void deleteWork_Schedule(Integer idWS) {
        work_scheduleRepository.deleteById(idWS);
    }

    @Override
    public List<Work_Schedule> retrieveAllWork_Schedule() {
        List<Work_Schedule> workSchedules = new ArrayList<>();
        work_scheduleRepository.findAll().forEach(workSchedules::add);
        return workSchedules;
    }

    @Override
    public Work_Schedule retrieveWork_ScheduleById(Integer idWS) {
        return work_scheduleRepository.findById(idWS).orElse(null);
    }
}
