package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Offer;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Supplier;
import tn.esprit.Exception.ElementNotFoundException;
import tn.esprit.Interface.IOfferService;
import tn.esprit.Repository.IOfferCallRepository;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return callRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Offer with id "+ id +" not found : " ));
    }

    @Override
    public Set<Offer> getAllOfferCalls() {
        Set<Offer> offers = new HashSet<>();
        callRepository.findAll().forEach(offers::add);
        return offers;
    }

    @Override
    public Offer AffectProductToOfferCall(Long idOffer,Long idPro) {
        Offer offer=callRepository.findById(idOffer).orElseThrow(() -> new ElementNotFoundException("Offer with id "+ idOffer +" not found : " ));
        Product product=productRepository.findById(idPro).orElseThrow(() -> new ElementNotFoundException("Product Ligne with id "+ idPro +" not found : " ));
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
        Offer offer =callRepository.findById(idOffer).orElseThrow(() -> new ElementNotFoundException("Offer  with id "+ idOffer +" not found : " ));
        Supplier supplier=supplierRepository.findById(idSupp).orElseThrow(() -> new ElementNotFoundException("Supplier  with id "+ idSupp +" not found : " ));
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
        Product product=productRepository.findById(idPro).orElseThrow(() -> new ElementNotFoundException("Product  with id "+ idPro +" not found : " ));;
        Supplier supplier=supplierRepository.findById(idSupp).orElseThrow(() -> new ElementNotFoundException("Supplierwith id "+ idSupp +" not found : " ));;
        if(offer.getProduct()==null&& offer.getSupplier()==null){
            offer.setProduct(product);
            offer.setSupplier(supplier);
            offer.setPrix(product.getPrice());
            callRepository.save(offer);
        }else {
            offer.getProduct();
        }
        return offer;
    }

}
