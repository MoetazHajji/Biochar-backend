package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.CommandLigne;

@Service
public interface ICommandLigneService {
    CommandLigne AddLigneAndAssign(CommandLigne ligne,Long idProd);
}
