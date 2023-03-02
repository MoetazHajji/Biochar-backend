package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;
import tn.esprit.Entity.Type_product;
import tn.esprit.Interface.IStockService;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockService implements IStockService {
    IStockRepository stockRepository;
    IProductRepository productRepository;

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock modifyStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<>();
        stockRepository.findAll().forEach(stockList::add);
        return stockList;
    }

    @Override
    public Long getNbProductsInStock(Long id) {
        return stockRepository.NbProductsInStock(id);
    }

    @Override
    public Double getSumSizeOfProducts(Long id) {
        return stockRepository.getSumSizeOfProducts(id);
    }



    @Override
    public Stock AffectProductToSupplies(Long idPro,Long quantity ,Long idStock) {
        Product product=productRepository.findById(idPro).orElse(null);
        Stock stock =stockRepository.findById(idStock).orElse(null);
        product.setQuantity_stock(quantity);
        stock.getProducts().add(product);
        stockRepository.save(stock);
        if (stock.getType_product().equals(Type_product.REAGENT) ) {
            Long nbProducts = stockRepository.NbProductsInStock(idStock);
            Double productsSize = stockRepository.getSumSizeOfProducts(idStock) ;
            Double totQantity = stockRepository.getTotalQantity(idStock);
            stock.setUsed_storage(productsSize);
            stock.setNbProduct(nbProducts);
            stock.setTotal_quantity(totQantity);
            Double freeStorage=stock.getStorage() - productsSize;
            stock.setFree_storage(freeStorage);
            stockRepository.save(stock);
        }
        if(stock.getType_product().equals(Type_product.EQUIPEMENT)){
            Long nbProducts = stockRepository.NbProductsInStock(idStock);
            Double totQantity = stockRepository.getTotalQantity(idStock);
            stock.setNbProduct(nbProducts);
            stock.setTotal_quantity(totQantity);
            stockRepository.save(stock);
        }
        return stock;
    }


}
