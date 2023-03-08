package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Entity.Product;
import tn.esprit.Exception.ElementNotFoundException;
import tn.esprit.Interface.ICommandLigneService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.IProductRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CommandLigneService implements ICommandLigneService {

    ICommandLigneRepository ligneRepository;
    IProductRepository productRepository;

    @Override
    public CommandLigne AddLigneAndAssign(CommandLigne ligne, Long idProd) {
        Product product = productRepository.findById(idProd).orElseThrow(() -> new ElementNotFoundException("Product with id "+ idProd +" not found : " ));
        ligne.setProduct(product);
        if(product.getCount_order()==null){
            product.setCount_order(1L);
            productRepository.save(product);
        }else {
            Long CountToUpdate=product.getCount_order();
            CountToUpdate++;
            product.setCount_order(CountToUpdate);
        }
        ligneRepository.save(ligne);
        if (ligne.getProduct()!=null && ligne.getCommand()!=null){
            Double QuantitySumPerProduct = ligneRepository.SumOfProductQuantity(idProd) +product.getQuantity();
            product.setQuantity(QuantitySumPerProduct);
        }
        return ligne;
    }

    @Override
    public CommandLigne GetCommandById(Long id) {
        return ligneRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Command with id "+ id +" not found : " ));
    }

    @Override
    public Set<CommandLigne> getAllOrdersLine() {
        Set<CommandLigne> ligneList = new HashSet<>();
        ligneRepository.findAll().forEach(ligneList::add);
        return ligneList;
    }

    @Override
    public void deleteOrderLine(Long id) {
        ligneRepository.deleteById(id);
    }

    @Override
    public Double getSumQuantity(Long id) {
        return ligneRepository.SumOfProductQuantity(id);
    }


}
