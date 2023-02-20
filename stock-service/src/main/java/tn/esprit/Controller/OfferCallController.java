package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Offer;
import tn.esprit.Interface.IOfferService;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Offer calls management")
@RequestMapping("offer")
public class OfferCallController {
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
    public List<Offer> getAllOfferCalls(){
        return callService.getAllOfferCalls();
    }

    @Operation(description = "Add Offer call and assign Product")
    @PostMapping("addCallAndAssignProduct/{idPro}/{idSupp}")
    public Offer addOfferAndAssignProduct(@RequestBody Offer offer, @PathVariable("idPro") Long idPro, @PathVariable("idSupp") Long idSupp){
        return callService.addOfferAndAssignProductSupplier(offer,idPro,idSupp);
    }
}
