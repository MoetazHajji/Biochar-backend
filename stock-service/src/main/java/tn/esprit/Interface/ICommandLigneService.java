package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.CommandLigne;

import java.util.List;

@Service
public interface ICommandLigneService {
    CommandLigne AddLigneAndAssign(CommandLigne ligne,Long idProd);

    CommandLigne GetCommandById(Long id);

    List<CommandLigne> getAllOrdersLine();

    void deleteOrderLine(Long id);
}
