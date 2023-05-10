package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Offer;

import java.util.List;
import java.util.Set;

@Service
public interface IOfferService {
    Offer addOfferCall(Offer offer);
    Offer modifyOfferCall(Offer offer);
    void deleteOfferCall(Long id);
    Offer getOfferCallById(Long id);
    Set<Offer> getAllOfferCalls();
    Offer AffectProductToOfferCall( Long idOffer,Long idPro);
    Offer addOfferAndAssignProductSupplier(Offer offer, Long idPro, Long idSupp);
    Offer AffectSupplierToOfferCall(Long idOffer, Long idSupp);
    List<Offer> getOffersForProduct(Long idProd);
}
