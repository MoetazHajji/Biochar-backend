package tn.esprit.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Dto.Work_ScheduleDto;
import tn.esprit.Entity.Work_Schedule;
import tn.esprit.Interface.IWork_ScheduleService;
import tn.esprit.Mapper.Work_ScheduleMapper;
import tn.esprit.Repository.Work_ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Work_ScheduleService implements IWork_ScheduleService {

    private final Work_ScheduleRepository work_scheduleRepository;

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
}
