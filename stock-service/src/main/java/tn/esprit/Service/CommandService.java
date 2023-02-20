package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.ICommandService;
import tn.esprit.Repository.ICommandRepository;
import tn.esprit.Repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandService implements ICommandService {

    ICommandRepository commandRepository;
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
        return commandRepository.findById(id).orElse(null);
    }

    @Override
    public List<Command> getAllCommands() {
        List<Command> commandList =new ArrayList<>();
        commandRepository.findAll().forEach(commandList::add);
        return commandList;
    }

    @Override
    public void affectproductsToCommand(Long idCom,Long idPro) {
        Product product=productRepository.findById(idPro).orElse(null);
        Command command=commandRepository.findById(idCom).orElse(null);
            command.getProducts().add(product);
            commandRepository.save(command);
    }

    @Override
    public void disaffectproductFromCommand(Long idCom, Long idPro) {
        Command command= commandRepository.findById(idCom).orElse(null);
        int productNb=command.getProducts().size();
        for (int index=0;index<productNb;index++){
            if(command.getProducts().get(index).getId()==idPro){
                command.getProducts().remove(index);
                break;
            }
        }
        commandRepository.save(command);
    }

    @Override
    public List<Long> addCommandAndAffectProducts(Command command, List<Long> idPro) {
        List<Product> productList=new ArrayList<>();
        for (Long idProduct: idPro){
            Product product=productRepository.findById(idProduct).orElse(null);
            productList.add(product);
            command.setProducts(productList);
        }
        commandRepository.save(command);
        return idPro;
    }


}
