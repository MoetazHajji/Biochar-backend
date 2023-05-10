package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Entity.Product;
import tn.esprit.Exception.ElementNotFoundException;
import tn.esprit.Exception.NoProductException;
import tn.esprit.Interface.ICommandService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.ICommandRepository;
import tn.esprit.Repository.IProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CommandService implements ICommandService {

    ICommandRepository commandRepository;
    ICommandLigneRepository ligneRepository;
    IProductRepository productRepository;
    @Override
    public Command addCommand(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public Command modifyCommand(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public void deleteCommand(Long id) {
            commandRepository.deleteById(id);
    }

    @Override
    public Command getCommandById(Long id) {
        return commandRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Command with id "+ id +" not found : " ));
    }

    @Override
    public Set<Command> getAllCommands() {
        Set<Command> commandList =new HashSet<>();
        commandRepository.findAll().forEach(commandList::add);
        return commandList;
    }

    @Override
    @Transactional
    public Command affectCommandToCommandLine(Command command, List<Long> idCommandLines) {
        commandRepository.save(command);
        command.setDate(LocalDate.now());
        //List<CommandLigne> ligneList = command.getCommandLignes();
        command.setCommandLignes(null);
        for (Long idCommandLine:idCommandLines)
        {
            CommandLigne commandLigne = ligneRepository.findById(idCommandLine).orElseThrow(() -> new ElementNotFoundException("Command Ligne with id "+ idCommandLine +" not found : " ));
            commandLigne.setCommand(command);
            ligneRepository.save(commandLigne);
        }

        if(command.getTotal_price()==null && command.getQuantity_product()==null){
            Long commandId = command.getId();
            Double totprix=commandRepository.calculSumPriceProducts(commandId);
            Long nbProducts = commandRepository.getNumberProducts(commandId);
            Long TotQuantity = commandRepository.TotalQuantityOfProducts(commandId);
            command.setTotal_price(totprix);
            command.setNbPoduct(nbProducts);
            command.setQuantity_product(TotQuantity);
            commandRepository.save(command);
        }
        return  command;
    }

    @Override
    @Transactional
    public void disaffectCommandFromOrderLine(Long idCom, Long idComL) {
        Command command= commandRepository.findById(idCom).orElseThrow(() -> new ElementNotFoundException("Command with id "+ idCom +" not found : " ));
        Set<Product> productList = productRepository.findProductsByCommandId(idCom);
        int productNb=command.getCommandLignes().size();
        for (int index=0;index<productNb;index++){
            for (CommandLigne ligne:command.getCommandLignes())
                if(ligne.getId()==idComL) {
                    command.getCommandLignes().remove(index);
                    for (Product product : productList) {
                        if(product.getCount_order()!=null){
                            Long CountToUpdate = product.getCount_order();
                            CountToUpdate--;
                            product.setCount_order(CountToUpdate);
                            Double UpdateQuanity = product.getQuantity() - ligneRepository.SumOfProductQuantity(product.getId());
                            product.setQuantity(UpdateQuanity);
                        }else if(product.getCount_order() < 0){
                            throw new NoProductException("Count order connot be neagtive");
                        }
                        commandRepository.save(command);
                        break;
                    }
                }
        }
        commandRepository.save(command);
    }

    @Override
    public Set<CommandLigne> getCommandLigneOfCommand(Long idCom) {
        Command command = commandRepository.findById(idCom).orElseThrow(() -> new ElementNotFoundException("Command with id "+ idCom +" not found : " ));
        return command.getCommandLignes();
    }

    /*@Override
    public void affectproductsToCommand(Long idCom,Long idPro) {
        Product product=productRepository.findById(idPro).orElse(null);
        Command command=commandRepository.findById(idCom).orElse(null);
            command.getProducts().add(product);
            commandRepository.save(command);
    }*/

   /* @Override
    public Command addCommandAndAffectProducts(Command command, List<Long> idPro) {
        List<Product> productList=new ArrayList<>();
        for (Long idProduct: idPro){
            Product product=productRepository.findById(idProduct).orElse(null);
            productList.add(product);
            command.setProducts(productList);
        }
        commandRepository.save(command);
        if(command.getTotal_price()==null && command.getQuantity_product()==null){
        Long commandId = command.getId();
        Double totprix=commandRepository.calculSumPriceProducts(commandId);
        Long nbProducts = commandRepository.getNumberProducts(commandId);
        Long TotQuantity = commandRepository.TotalQuantityOfProducts(commandId);
        command.setTotal_price(totprix);
        command.setNbPoduct(nbProducts);
        command.setQuantity_product(TotQuantity);
        commandRepository.save(command);
        }
        return command;
    }

    @Override
    public Command SetTotPriceCommand(Long id) {
        Command command=commandRepository.findById(id).orElse(null);
        Double totpri=commandRepository.calculSumPriceProducts(id);
        command.setTotal_price(totpri);
        commandRepository.save(command);
        return command;
    }*/

}
