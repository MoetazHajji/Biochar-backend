package tn.esprit.Mappers;

import tn.esprit.Entity.Adress;
import tn.esprit.dto.AdressDto;

public class AdressMapper {
    public static AdressDto mapToDo(Adress adress){
        AdressDto adressDto=AdressDto.builder()
                .id(adress.getId())
                .city(adress.getCity())
                .country(adress.getCountry())
                .Street(adress.getStreet())
                .postal_code(adress.getPostal_code())
                .supplier_name(adress.getSupplier().getName_supplier())
                .supplier_id(adress.getSupplier().getId())
                .build();
        return adressDto;
    }
    public static Adress mapToEntity(AdressDto adressDto){
        Adress adress=Adress.builder()
                .id(adressDto.getId())
                .city(adressDto.getCity())
                .country(adressDto.getCountry())
                .postal_code(adressDto.getPostal_code())
                .Street(adressDto.getStreet())
                .build();
        return adress;
    }
}
