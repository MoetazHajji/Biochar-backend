package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.ICommandService;
import tn.esprit.Interface.IProductService;
import tn.esprit.Service.PDFGeneratorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Tag(name = "Command Management")
@AllArgsConstructor
@RestController
@RequestMapping("/command")
public class CommandController {
    ICommandService commandService;
    IProductService productService;
    PDFGeneratorService pdfGeneratorService;

    @Operation(description = "Add new Command")
    @PostMapping("add")
    public Command addCommand(@RequestBody Command command){
        return commandService.addCommand(command);
    }

    @Operation(description = "Modify Command")
    @PutMapping("modify")
    public Command modifyCommand(@RequestBody Command command){
        return commandService.modifyCommand(command);
    }

    @Operation(description = "Delete Command")
    @DeleteMapping("delete/{id}")
    public void deleteCommand(@PathVariable("id") Long id){
        commandService.deleteCommand(id);
    }

    @Operation(description = "Retreive Command by Id")
    @GetMapping("getAdress/{id}")
    public Command getCommandById(@PathVariable("id") Long id){
        return commandService.getCommandById(id);
    }

    @Operation(description = "Retreive all Commands")
    @GetMapping("getAllAdress")
    public Set<Command> getAllCommand(){
        return commandService.getAllCommands();
    }
    @PostMapping("AddCommandAndAssignToCommandLigne/{id}")
    public Command addCommandAndAffectProducts(@RequestBody Command command, @PathVariable("id") List<Long> idCommandLignes){
        return commandService.affectCommandToCommandLine(command,idCommandLignes);
    }
    @PutMapping("desaffectProdutcsFromEmployee/{idCom}/{idComL}")
    public void disaffectproductFromCommand(@PathVariable("idCom") Long idCom,@PathVariable("idComL") Long idComL){
        commandService.disaffectCommandFromOrderLine(idCom,idComL);
    }

    @GetMapping("/pdf/generate/{id}")
    public void generatePDF(HttpServletResponse response,@PathVariable("id") Long id) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response,id);
    }



    /*@PutMapping("affectProductsToCommand/{idCom}/{idPro}")
    public void affectproductsToCommand(@PathVariable("idCom") Long idCom,@PathVariable("idPro") Long idPro){
         commandService.affectproductsToCommand(idPro, idCom);
    }


    @PutMapping("setTotalPrice/{id}")
    public Command SetTotPriceCommand(@PathVariable("id") Long id){
        return commandService.SetTotPriceCommand(id);
    }*/
}
