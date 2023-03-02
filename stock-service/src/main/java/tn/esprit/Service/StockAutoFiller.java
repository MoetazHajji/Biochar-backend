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

@AllArgsConstructor
@Service
@Slf4j
public class StockAutoFiller implements IStockAutoFiller {

    IProductService productService;
    IStockService stockService;
    ICommandLigneService ligneService;


    /*@Override
    @Scheduled(fixedRate = 1000)
    public void checkStockLevels() {
        List<Product> productList=productService.getProductList();
        List<Stock> stockList = stockService.getAllStocks();
        for (Stock stock:stockList) {
            for (Product product : productList) {
                if (product.getType_product().equals(Type_product.REAGENT)) {
                    if (stock.getStorage() < product.getAutoFillQuantity()) {
                        Long quantityToAdd = product.getAutoFillQuantity();
                        Double availableStockCapacity = stock.getStorage() - stock.getUsed_storage();
                        if(availableStockCapacity > quantityToAdd){
                            quantityToAdd = availableStockCapacity;
                        }
                        stockService.AffectProductToSupplies(product.getId(), quantityToAdd, stock.getId());
                        stock.setState(State.AVAILABLE);
                    } else if (product.getStocks().get(0).getState().equals(State.OUT_OF_STOCK)) {
                        ligneService.AddLigneAndAssign(ligne,product.getId());
                    }
                }
            }
        }
    }*/
}
