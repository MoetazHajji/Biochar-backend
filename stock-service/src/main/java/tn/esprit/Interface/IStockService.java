package tn.esprit.Interface;


import org.springframework.stereotype.Service;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;

import java.util.List;

@Service
public interface IStockService {
    Stock addStock(Stock stock);
    Stock modifyStock(Stock stock);
    void deleteStock(Long id);
    Stock getStockById(Long id);
    List<Stock> getAllStocks();
    Stock AffectProductToSupplies(Long idPro, Long idSupp);
    Long getNbProductsInStock(Long id);
    Double getSumSizeOfProducts(Long id);

}
