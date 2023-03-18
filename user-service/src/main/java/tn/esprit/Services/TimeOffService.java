package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.TypeTimeOff;
import tn.esprit.Entitys.User;
import tn.esprit.Repositorys.TimeOffRepository;
import tn.esprit.Repositorys.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service("TimeOff")
public class TimeOffService implements ITimeOffService{
    private TimeOffRepository timeOffRepository;
    @Autowired // Methode 2
    public TimeOffService(TimeOffRepository timeOffRepository  )
    {
        this.timeOffRepository = timeOffRepository;
    }

    @Override
    public List<TimeOff> SelectAll() {
        return timeOffRepository.findAll();
    }

    @Override
    public TimeOff SelectBy(long id) {
        TimeOff timeOff = timeOffRepository.findById(id).orElse(null)  ;
        return timeOff;
    }

    @Override
    public TimeOff Insert(TimeOff object) {

        object.setTypeTimeOff(this.getTypeTimeOff(object));
        return timeOffRepository.save(object);
    }

    @Override
    public  TimeOff  update(TimeOff object) {
        TimeOff timeOff = timeOffRepository.findById(object.getId()).orElse(null)  ;
        if (timeOff == null  ){  return  timeOffRepository.save(timeOff) ; }
        else{ return  timeOffRepository.save(object) ;}
    }

    @Override
    public boolean delete(long id) {
        boolean deleted = false;
        TimeOff timeOff  = timeOffRepository.findById(id).orElse(null) ;
        if (timeOff != null ) {
            timeOffRepository.delete(timeOff);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        timeOffRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public TypeTimeOff getTypeTimeOff(TimeOff object) {
        TypeTimeOff typeTimeOff = null ;
        if ((object.getStartDate() == null && object.getEndDate() == null )&&(object.getStartTime() != null && object.getEndTime() != null ))
        {typeTimeOff = TypeTimeOff.Time ;}
        if ((object.getStartDate() != null && object.getEndDate() != null )&&(object.getStartTime() == null && object.getEndTime() == null ))
        {typeTimeOff = TypeTimeOff.Date;}
        if ((object.getStartDate() != null && object.getEndDate() != null )&&(object.getStartTime() != null && object.getEndTime() != null ))
        {typeTimeOff = TypeTimeOff.DateTime;}
        return typeTimeOff;
    }

    @Override
    public  boolean isWeekend(LocalDate localDate) {

        // get Day of week for the passed LocalDate
        return (localDate.get(ChronoField.DAY_OF_WEEK) == 6)
                || (localDate.get(ChronoField.DAY_OF_WEEK) == 7);
    }



    public boolean verify (final LocalDate date , LocalTime startTime , LocalTime endTime )
    {
        boolean state = true ;
        if (this.isWeekend(date)){state = false;}
        if (timeOffRepository. isInThatDate(date) ){state = false;}
        if (timeOffRepository. isInBetweenTwoTime(startTime , endTime ) ){state = false;}
        return state;
    }
}
