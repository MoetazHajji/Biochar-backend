package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.OfferCall;
import tn.esprit.Interface.IOfferCallService;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Offer calls management")
@RequestMapping("offer")
public class OfferCallController {
    IOfferCallService callService;

    @Operation(description = "Add new Offer Call")
    @PostMapping("add")
    public OfferCall addOfferCall(@RequestBody OfferCall offerCall){
        return callService.addOfferCall(offerCall);
    }

    @Operation(description = "Modify Offer Call")
    @PutMapping("modify")
    public OfferCall modifyOfferCall(@RequestBody OfferCall offerCall){
        return callService.modifyOfferCall(offerCall);
    }

    @Operation(description = "Delete Command")
    @DeleteMapping("delete/{id}")
    public void deleteOfferCall(@PathVariable("id") Long id){
        callService.deleteOfferCall(id);
    }

    @Operation(description = "Retreive Offer Call by Id")
    @GetMapping("getAdress/{id}")
    public OfferCall getOfferCallById(@PathVariable("id") Long id){
        return callService.getOfferCallById(id);
    }

    @Operation(description = "Retreive all Commands")
    @GetMapping("getAllAdress")
    public List<OfferCall> getAllOfferCalls(){
        return callService.getAllOfferCalls();
    }

    @Operation(description = "Add Offer call and assign Product")
    @PostMapping("addCallAndAssignProduct/{id}")
    public OfferCall addOfferAndAssignProduct(@RequestBody OfferCall offerCall,@PathVariable("id") Long idPro){
        return callService.addOfferAndAssignProduct(offerCall,idPro);
    }
}
