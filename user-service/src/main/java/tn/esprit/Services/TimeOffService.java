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
    public ResponseEntity<TimeOff> SelectBy(long id) {
        TimeOff timeOff = timeOffRepository.findById(id).orElse(null)  ;
        return ResponseEntity.ok(timeOff);
    }

    @Override
    public TimeOff Insert(TimeOff object) {

        object.setTypeTimeOff(this.getTypeTimeOff(object));
        return timeOffRepository.save(object);
    }

    @Override
    public ResponseEntity<TimeOff> update(TimeOff object) {
        TimeOff timeOff = timeOffRepository.findById(object.getId()).orElse(null)  ;
        if (timeOff == null  ){  return ResponseEntity.ok(timeOffRepository.save(timeOff)); }
        else{ return ResponseEntity.ok(timeOffRepository.save(object));}
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        TimeOff timeOff  = timeOffRepository.findById(id).orElse(null) ;
        timeOffRepository.delete(timeOff);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        timeOffRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public TypeTimeOff getTypeTimeOff(TimeOff object) {
        TypeTimeOff typeTimeOff = null ;
        if ((object.getStart_date() == null && object.getEnd_date() == null )&&(object.getStart_time() != null && object.getEnd_time() != null ))
        {typeTimeOff = TypeTimeOff.Time ;}
        if ((object.getStart_date() != null && object.getEnd_date() != null )&&(object.getStart_time() == null && object.getEnd_time() == null ))
        {typeTimeOff = TypeTimeOff.Date;}
        if ((object.getStart_date() != null && object.getEnd_date() != null )&&(object.getStart_time() != null && object.getEnd_time() != null ))
        {typeTimeOff = TypeTimeOff.DateTime;}
        return typeTimeOff;
    }
    @Override
    public boolean isWeekend(final Date d)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }

    public boolean verify (final Date date , Date startTime , Date endTime )
    {
        boolean state = true ;
        if (this.isWeekend(date)){state = false;}
        if (timeOffRepository. isInThatDate(date) ){state = false;}
        if (timeOffRepository. isInBetweenTwoTime(startTime , endTime ) ){state = false;}
        return state;
    }
}
