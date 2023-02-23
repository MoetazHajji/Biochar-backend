package tn.esprit.Services;

import tn.esprit.Entitys.TimeOff;
import tn.esprit.Entitys.TypeTimeOff;

import java.util.Date;

public interface ITimeOffService extends IGenericCRUD<TimeOff>{
    boolean isWeekend(final Date d);
    TypeTimeOff getTypeTimeOff(TimeOff object);
    boolean verify (final Date date , Date startTime , Date endTime );
}
