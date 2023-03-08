package tn.esprit.Services;

import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.TypeTimeOff;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public interface ITimeOffService extends IGenericCRUD<TimeOff>{
    boolean isWeekend(final LocalDate d);
    TypeTimeOff getTypeTimeOff(TimeOff object);
    boolean verify (final LocalDate date , LocalTime startTime , LocalTime endTime );
}
