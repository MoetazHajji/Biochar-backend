package tn.esprit.Mapper;



import tn.esprit.Dto.DemandDto;
import tn.esprit.Entity.Demand;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Repository.TrainingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DemandMapper {
    public static Demand mapToEntity(DemandDto demandDto, ITrainingService trainingService){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Demand demand = Demand.builder()
                .id(demandDto.getId_demand())
                .description_r(demandDto.getDescription())
                .date_d(date)
                .training(trainingService.get_By_Title_training(demandDto.getTraining()))
                .build();
        return demand;
    }


    public static DemandDto mapToDto(Demand demand){
        String title = "";
        if(demand.getTraining() != null)
            title = demand.getTraining().getTitle();

        DemandDto demandDto = DemandDto.builder()
                .id_demand(demand.getId())
                .description(demand.getDescription_r())
                .date(demand.getDate_d())
                .training(title)
                .build();
        return demandDto;
    }
}
