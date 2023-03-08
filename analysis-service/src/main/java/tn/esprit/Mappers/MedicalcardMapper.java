package tn.esprit.Mappers;

import tn.esprit.Dto.MedicalcardDto;
import tn.esprit.Entity.Medicalcard;

public class MedicalcardMapper {
        public static MedicalcardDto mapToDto(Medicalcard medicalcard){

            MedicalcardDto medicalcardDto = MedicalcardDto.builder()
                    .idMedicalcard(medicalcard.getIdMedicalcard())
                    .card_number(medicalcard.getCard_number())
                    .date_creation(medicalcard.getDate_creation())
                    .review(medicalcard.getReview())
                    .Doc(medicalcard.getDoc())
                    .build();
            return medicalcardDto;
        }
        public static Medicalcard mapToEntity(MedicalcardDto medicalcardDto ){
            Medicalcard medicalcard = Medicalcard.builder()
                    .idMedicalcard(medicalcardDto.getIdMedicalcard())
                    .card_number(medicalcardDto.getCard_number())
                    .date_creation(medicalcardDto.getDate_creation())
                    .review(medicalcardDto.getReview())
                    .Doc(medicalcardDto.getDoc())
                    .build();
            return medicalcard;
        }


}
