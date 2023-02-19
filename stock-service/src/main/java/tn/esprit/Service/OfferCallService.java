package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.OfferCall;
import tn.esprit.Interface.IOfferCallService;
import tn.esprit.Repository.IOfferCallRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferCallService implements IOfferCallService {
    IOfferCallRepository callRepository;
    @Override
    public OfferCall addOfferCall(OfferCall offerCall) {
        return callRepository.save(offerCall);
    }

    @Override
    public OfferCall modifyOfferCall(OfferCall offerCall) {
        return callRepository.save(offerCall);
    }

    @Override
    public void deleteOfferCall(Long id) {
        callRepository.deleteById(id);
    }

    @Override
    public OfferCall getOfferCallById(Long id) {
        return callRepository.findById(id).orElse(null);
    }

    @Override
    public List<OfferCall> getAllOfferCalls() {
        List<OfferCall> offerCalls = new ArrayList<>();
        callRepository.findAll().forEach(offerCalls::add);
        return offerCalls;
    }
}
