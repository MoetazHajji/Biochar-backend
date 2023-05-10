package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Offer;
import tn.esprit.Interface.IOfferService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@Tag(name = "Offer calls management")
@RequestMapping("offer")
public class OfferController {
    IOfferService callService;

    @Operation(description = "Add new Offer Call")
    @PostMapping("add")
    public Offer addOfferCall(@RequestBody Offer offer){
        return callService.addOfferCall(offer);
    }

    @Operation(description = "Modify Offer Call")
    @PutMapping("modify")
    public Offer modifyOfferCall(@RequestBody Offer offer){
        return callService.modifyOfferCall(offer);
    }

    @Operation(description = "Delete Command")
    @DeleteMapping("delete/{id}")
    public void deleteOfferCall(@PathVariable("id") Long id){
        callService.deleteOfferCall(id);
    }

    @Operation(description = "Retreive Offer Call by Id")
    @GetMapping("getAdress/{id}")
    public Offer getOfferCallById(@PathVariable("id") Long id){
        return callService.getOfferCallById(id);
    }

    @Operation(description = "Retreive all Commands")
    @GetMapping("getAllAdress")
    public Set<Offer> getAllOfferCalls(){
        return callService.getAllOfferCalls();
    }

    @Operation(description = "Add Offer call and assign Product and supplier")
        @PostMapping("addCallAndAssignProduct/{idPro}/{idSupp}")
    public Offer addOfferAndAssignProduct(@RequestBody Offer offer, @PathVariable("idPro") Long idPro, @PathVariable("idSupp") Long idSupp){
        return callService.addOfferAndAssignProductSupplier(offer,idPro,idSupp);
    }

    @Operation(description = "Assign Product to Offer")
    @PutMapping("AssignProductToOffer/{idOffer}/{idPro}")
    public Offer AssignProductToOffer( @PathVariable("idOffer") Long idOffer, @PathVariable("idPro") Long idPro){
        return callService.AffectProductToOfferCall(idOffer,idPro);
    }

    @Operation(description = "Assign Supplier to Offer")
    @PutMapping("AssignSupplierToOffer/{idOffer}/{idsupp}")
    public Offer AssignSupplierToOffer( @PathVariable("idOffer") Long idOffer, @PathVariable("idPro") Long idSupp){
        return callService.AffectProductToOfferCall(idOffer,idSupp);
    }


}
