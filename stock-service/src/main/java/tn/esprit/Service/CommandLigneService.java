package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.CommandLigne;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.ICommandLigneService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.IProductRepository;

@Service
@AllArgsConstructor
public class CommandLigneService implements ICommandLigneService {

    ICommandLigneRepository ligneRepository;
    IProductRepository productRepository;

    @Override
    public CommandLigne AddLigneAndAssign(CommandLigne ligne, Long idProd) {
        Product product = productRepository.findById(idProd).orElse(null);
        ligne.setProduct(product);
        ligneRepository.save(ligne);
        return ligne;
    }
}
