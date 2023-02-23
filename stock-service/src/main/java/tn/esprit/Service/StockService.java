package tn.esprit.Service;

import lombok.AllArgsConstructor;
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
    public Stock AffectProductToSupplies(Long idPro, Long idStock) {
        Product product=productRepository.findById(idPro).orElse(null);
        Stock stock =stockRepository.findById(idStock).orElse(null);
        //List<Product> productList = new ArrayList<>();
        //productList.add(product);
        stock.getProducts().add(product);
        stockRepository.save(stock);
        if (stock.getType_product().equals(Type_product.REAGENT) ) {
            Long nbProducts = stockRepository.NbProductsInStock(idStock);
            Double productsSize = stockRepository.getSumSizeOfProducts(idStock) ;
            stock.setUsed_storage(productsSize);
            stock.setNbProduct(nbProducts);
            Double freeStorage=stock.getStorage() - productsSize;
            stock.setFree_storage(freeStorage);
            stockRepository.save(stock);
        }
        return stock;
    }

}
