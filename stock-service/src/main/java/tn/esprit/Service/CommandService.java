package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.ICommandService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.ICommandRepository;
import tn.esprit.Repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandService implements ICommandService {

    ICommandRepository commandRepository;
    ICommandLigneRepository ligneRepository;
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
        return commandRepository.findById(id).orElse(null);
    }

    @Override
    public List<Command> getAllCommands() {
        List<Command> commandList =new ArrayList<>();
        commandRepository.findAll().forEach(commandList::add);
        return commandList;
    }

    @Override
    @Transactional
    public Command affectCommandToCommandLine(Command command, List<Long> idCommandLines) {
        commandRepository.save(command);
        //List<CommandLigne> ligneList = command.getCommandLignes();
        command.setCommandLignes(null);
        for (Long idCommandLine:idCommandLines)
        {
                CommandLigne commandLigne = ligneRepository.findById(idCommandLine).orElse(null);
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
    public void disaffectCommandFromOrderLine(Long idCom, Long idComL) {
        Command command= commandRepository.findById(idCom).orElse(null);
        int productNb=command.getCommandLignes().size();
        for (int index=0;index<productNb;index++){
            if(command.getCommandLignes().get(index).getId()==idComL){
                command.getCommandLignes().remove(index);
                break;
            }
        }
        commandRepository.save(command);
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
