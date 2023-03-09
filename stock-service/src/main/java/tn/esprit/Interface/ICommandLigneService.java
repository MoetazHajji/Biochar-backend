package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.CommandLigne;

import java.util.List;
import java.util.Set;

@Service
public interface ICommandLigneService {
    CommandLigne AddLigneAndAssign(CommandLigne ligne,Long idProd);

    CommandLigne GetCommandById(Long id);

    Set<CommandLigne> getAllOrdersLine();

    void deleteOrderLine(Long id);

    Double getSumQuantity(Long id);
}
