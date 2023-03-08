package tn.esprit.Mappers;

import tn.esprit.Entity.Offer;
import tn.esprit.dto.OfferDto;

public class OfferMapper {
    public static OfferDto mapToDo(Offer offer){
        OfferDto offerDto=OfferDto.builder()
                .id(offer.getId())
                .start_date(offer.getStart_date())
                .prix(offer.getPrix())
                .end_date(offer.getEnd_date())
                .quantity(offer.getQuantity())
                .id_supplier(offer.getSupplier().getId())
                .name_product(offer.getProduct().getName_product())
                .build();
        return offerDto;
    }
    public static Offer mapToEntity(OfferDto offerDto){
        Offer offer=Offer.builder()
                .prix(offerDto.getPrix())
                .start_date(offerDto.getStart_date())
                .end_date(offerDto.getEnd_date())
                .quantity(offerDto.getQuantity())
                .build();
                
        return offer;
    }
}
