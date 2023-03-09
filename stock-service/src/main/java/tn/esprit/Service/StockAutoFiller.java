package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.*;
import tn.esprit.Exception.OutOfStockException;
import tn.esprit.Interface.ICommandLigneService;
import tn.esprit.Interface.IProductService;
import tn.esprit.Interface.IStockAutoFiller;
import tn.esprit.Interface.IStockService;
import tn.esprit.Repository.ICommandLigneRepository;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.IStockRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class StockAutoFiller implements IStockAutoFiller {

    IStockRepository stockRepository;
    IProductService productService;
    IStockService stockService;
    ICommandLigneService ligneService;


    @Override
    @Transactional
    @Scheduled(fixedDelay = 3000)
    public void checkStockLevels() {
        Set<Stock> stockList = stockService.getAllStocks();
        for (Stock stock:stockList){
            for (Product product:stock.getProducts()) {
                if (stock.getProducts() != null && stock.getFree_storage() != null && stock.getFree_storage() >= stock.getStorage() - 10 ) {
                    log.info("Stock : " + stock.getId() + "is out of stock");
                    log.info("the product needed : " + product.getId());
                    if (product.getQuantity() <= product.getAutoFillQuantity()){
                        stockService.AffectProductToSupplies(product.getId(), product.getQuantity(), stock.getId());
                    }else if (product.getQuantity() > product.getAutoFillQuantity()) {
                        stockService.AffectProductToSupplies(product.getId(), product.getAutoFillQuantity(), stock.getId());
                    }else if(product.getQuantity()<= product.getAutoFillQuantity()){
                        throw new OutOfStockException("We can't add product " + product.getName_product() + " because nothing left ");
                    }
                }
            }
        }

    }
}
