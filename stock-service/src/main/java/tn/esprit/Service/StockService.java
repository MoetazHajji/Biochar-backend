package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.State;
import tn.esprit.Entity.Stock;
import tn.esprit.Entity.Type_product;
import tn.esprit.Exception.NoProductException;
import tn.esprit.Exception.OutOfStockException;
import tn.esprit.Interface.IStockService;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<Stock> getAllStocks() {
        Set<Stock> stockList = new HashSet<>();
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
    @Transactional
    public Stock AffectProductToSupplies(Long idPro,Double quantity ,Long idStock) {
        Product product=productRepository.findById(idPro).orElse(null);
        Stock stock =stockRepository.findById(idStock).orElse(null);
        if(stock.getUsed_storage()==null){
            stock.setUsed_storage(quantity);
            stock.setFree_storage(stock.getStorage() - quantity);
            stockRepository.save(stock);
        }
        if (stock.getProducts()==null) {
            stock.setTotal_quantity(quantity);
            stock.getProducts().add(product);
            stockRepository.save(stock);
        }
        if (product.getType_product().equals(Type_product.REAGENT) && stock.getProducts()!=null && stock.getFree_storage() <= stock.getStorage()) {
            stock.getProducts().add(product);
            Double quantityWithSize = quantity * product.getSize_product();
            Double newQuantity = product.getQuantity() - quantityWithSize;
            Long nbProducts = stockRepository.NbProductsInStock(idStock);
            Double totQantity = stock.getTotal_quantity() +quantityWithSize;
            /************** storage ******************/
            Double freeStorge = stock.getFree_storage();
            Double usedStorage = stock.getUsed_storage();
            Double updatedUsedStorage = freeStorge - quantityWithSize;
            Double updatedFreeStorage = stock.getStorage() - totQantity;
            /************** storage ******************/
            stock.setNbProduct(nbProducts);
            stock.setTotal_quantity(totQantity);
            product.setQuantity(newQuantity);
            stock.setState(State.AVAILABLE);
            //Double productsSize = product.getSize_product() * quantity;
            stock.setUsed_storage(totQantity);
            stock.setFree_storage(updatedFreeStorage);
            stockRepository.save(stock);
        }
        else if(stock.getType_product().equals(Type_product.EQUIPEMENT) && stock.getProducts()!=null && stock.getTotal_quantity() <= stock.getStorage()){
            Long nbProducts = stockRepository.NbProductsInStock(idStock);
            Double totQantity = stockRepository.getTotalQantity(idStock);
            stock.setNbProduct(nbProducts);
            stock.setTotal_quantity(totQantity);
            stock.setState(State.AVAILABLE);
            stockRepository.save(stock);
        }  if (stock.getFree_storage() == stock.getStorage() ) {
            stock.setState(State.OUT_OF_STOCK);
            throw new OutOfStockException("Product is out of stock ");
        }
        return stock;
    }

    @Override
    public Stock withdrawStock(Double quantity, Long idstock) {
        Stock stock= stockRepository.findById(idstock).orElse(null);
        Double newQuantity = stock.getTotal_quantity() - quantity;
        Double newFreeQuantity = stock.getFree_storage() + quantity;
        Double newUsedQuantity = stock.getUsed_storage() - quantity;
        stock.setTotal_quantity(newQuantity);
        stock.setFree_storage(newFreeQuantity);
        stock.setUsed_storage(newUsedQuantity);
        for (Product product: stock.getProducts()) {
            if (product.getQuantity() < 0 || stock.getFree_storage() < 0 || stock.getUsed_storage() < 0 || stock.getTotal_quantity() < 0) {
                throw new NoProductException("quantity and Storage cannot be negative");
            }
            if (stock.getFree_storage() >= stock.getStorage() || stock.getUsed_storage() >= stock.getStorage()) {
                throw new OutOfStockException("Free and used storage can't over pass total storage ");
            }
        }
        stockRepository.save(stock);
        return stock;
    }


}
