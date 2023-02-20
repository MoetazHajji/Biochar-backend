package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Offer;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Supplier;
import tn.esprit.Interface.IOfferService;
import tn.esprit.Repository.IOfferCallRepository;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferService implements IOfferService {
    IOfferCallRepository callRepository;
    IProductRepository productRepository;
    ISupplierRepository supplierRepository;
    @Override
    public Offer addOfferCall(Offer offer) {
        return callRepository.save(offer);
    }

    @Override
    public Offer modifyOfferCall(Offer offer) {
        return callRepository.save(offer);
    }

    @Override
    public void deleteOfferCall(Long id) {
        callRepository.deleteById(id);
    }

    @Override
    public Offer getOfferCallById(Long id) {
        return callRepository.findById(id).orElse(null);
    }

    @Override
    public List<Offer> getAllOfferCalls() {
        List<Offer> offers = new ArrayList<>();
        callRepository.findAll().forEach(offers::add);
        return offers;
    }

    @Override
    public Offer AffectProductToOfferCall(Long idPro, Long idOffer) {
        Offer offer=callRepository.findById(idOffer).orElse(null);
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
    public Offer AffectSupplierToOfferCall(Long idOffer, Long idSupp) {
        Offer offer =callRepository.findById(idOffer).orElse(null);
        Supplier supplier=supplierRepository.findById(idSupp).orElse(null);
        if (offer.getSupplier()==null){
            offer.setSupplier(supplier);
            callRepository.save(offer);
        }else {
            offer.getSupplier();
        }
        return offer;
    }

    @Override
    public Offer addOfferAndAssignProductSupplier(Offer offer, Long idPro, Long idSupp) {
        Product product=productRepository.findById(idPro).orElse(null);
        Supplier supplier=supplierRepository.findById(idSupp).orElse(null);
        if(offer.getProduct()==null&& offer.getSupplier()==null){
            offer.setProduct(product);
            offer.setSupplier(supplier);
            callRepository.save(offer);
        }else {
            offer.getProduct();
        }
        return offer;
    }

}
