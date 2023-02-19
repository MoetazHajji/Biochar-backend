package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.OfferCall;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.IOfferCallService;
import tn.esprit.Repository.IOfferCallRepository;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferCallService implements IOfferCallService {
    IOfferCallRepository callRepository;
    IProductRepository productRepository;
    ISupplierRepository supplierRepository;
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

    @Override
    public OfferCall AffectProductToOfferCall(Long idPro,Long idOffer) {
        OfferCall offer=callRepository.findById(idOffer).orElse(null);
        Product product=productRepository.findById(idPro).orElse(null);
        if(offer.getProduct()==null){
            offer.setProduct(product);
            callRepository.save(offer);
        }else {
            offer.getProduct();
        }
        return offer;
    }

    @Override
    public OfferCall addOfferAndAssignProduct(OfferCall offerCall,Long idPro) {
        Product product=productRepository.findById(idPro).orElse(null);
        if(offerCall.getProduct()==null){
            offerCall.setProduct(product);
            callRepository.save(offerCall);
        }else {
            offerCall.getProduct();
        }
        return offerCall;
    }

    @Override
    public OfferCall AffectSupplierToOfferCall(Long idOffer, Long idSupp) {
        OfferCall offerCall=callRepository.findById(idOffer).orElse(null);
        Supplier supplier=supplierRepository.findById(idSupp).orElse(null);
        if (offerCall.getSupplier()==null){
            offerCall.setSupplier(supplier);
            callRepository.save(offerCall);
        }else {
            offerCall.getSupplier();
        }
        return null;
    }


}
