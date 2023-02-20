package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Offer;

import java.util.List;

@Service
public interface IOfferService {
    Offer addOfferCall(Offer offer);
    Offer modifyOfferCall(Offer offer);
    void deleteOfferCall(Long id);
    Offer getOfferCallById(Long id);
    List<Offer> getAllOfferCalls();
    Offer AffectProductToOfferCall(Long idPro, Long idOffer);
    Offer addOfferAndAssignProductSupplier(Offer offer, Long idPro, Long idSupp);
    Offer AffectSupplierToOfferCall(Long idOffer, Long idSupp);
}
