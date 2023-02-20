package tn.esprit.Interface;

import tn.esprit.Entity.Work_Schedule;

import java.util.List;

public interface IWork_ScheduleService {

    public Work_Schedule addWork_Schedule(Work_Schedule workSchedule);
    public Work_Schedule updateWork_Schedule(Work_Schedule workSchedule);
    public void deleteWork_Schedule(Integer idWS);
    public List<Work_Schedule> retrieveAllWork_Schedule();
    public Work_Schedule retrieveWork_ScheduleById(Integer idWS);

}
