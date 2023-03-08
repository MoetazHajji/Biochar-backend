package tn.esprit.Interface;


import org.springframework.stereotype.Service;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;

import java.util.List;
import java.util.Set;

@Service
public interface IStockService {
    Stock addStock(Stock stock);
    Stock modifyStock(Stock stock);
    void deleteStock(Long id);
    Stock getStockById(Long id);
    Set<Stock> getAllStocks();
    Stock AffectProductToSupplies(Long idPro,Double quantity ,Long idStock);
    Long getNbProductsInStock(Long id);
    Double getSumSizeOfProducts(Long id);
    Stock withdrawStock(Double quantity,Long idstock);

}
