package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.OfferCall;

import java.util.List;

@Service
public interface IOfferCallService {
    OfferCall addOfferCall(OfferCall offerCall);
    OfferCall modifyOfferCall(OfferCall offerCall);
    void deleteOfferCall(Long id);
    OfferCall getOfferCallById(Long id);
    List<OfferCall> getAllOfferCalls();
}
