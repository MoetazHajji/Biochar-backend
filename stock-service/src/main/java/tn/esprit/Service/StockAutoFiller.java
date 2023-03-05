package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.*;
import tn.esprit.Interface.ICommandLigneService;
import tn.esprit.Interface.IProductService;
import tn.esprit.Interface.IStockAutoFiller;
import tn.esprit.Interface.IStockService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.IProductRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class StockAutoFiller implements IStockAutoFiller {

    IProductService productService;
    IStockService stockService;
    ICommandLigneService ligneService;


    @Override
    @Scheduled(cron = "*/10 * * * * * ")
    public void checkStockLevels() {
        Set<Stock> stockList = stockService.getAllStocks();
        for (Stock stock:stockList){
            //for (Product product:stock.getProducts()) {
                if (stock.getProducts() != null && stock.getFree_storage() != null && stock.getFree_storage() <= 5.0) {
                    //stockService.AffectProductToSupplies(product.getId(), stock.getUsed_storage(), stock.getId());
                    log.info("Stock : " + stock.getId() + "is out of stock");
                }
            //}
        }

    }
}
