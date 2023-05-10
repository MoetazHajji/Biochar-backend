package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;
import tn.esprit.Interface.IStockService;
import tn.esprit.Service.StockService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Tag(name = "Stock Management")
@RequestMapping("/stock")
public class StockController {
    IStockService stocksService;
    StockService stockSer;

    @Operation(description = "Create new Stock")
    @PostMapping("add")
    public Stock addStock(@RequestBody Stock stock){
        return stocksService.addStock(stock);
    }

    @Operation(description = "Modify Stock")
    @PutMapping("modify")
    public Stock modifyStock(@RequestBody Stock stock){
        return stocksService.modifyStock(stock);
    }

    @Operation(description = "Delete Stock")
    @DeleteMapping("delete/{id}")
    public void deleteStock(@PathVariable("id") Long id){
        stocksService.deleteStock(id);
    }

    @Operation(description = "Retreive Stock by ID")
    @GetMapping("getStock/{id}")
    public Stock getStockById(@PathVariable("id") Long id){
        return stocksService.getStockById(id);
    }

    @Operation(description = "Retreive all Stock")
    @GetMapping("getAllStocks")
    public Set<Stock> getAllStock(){
        return stocksService.getAllStocks();
    }

    @Operation(description = "Affect product to stock")
        @PutMapping("AssignProductToStock/{idPro}/{idSto}")
    public Stock AffectProductToSupplies(@PathVariable("idPro") Long idPro,@RequestBody Double quantity, @PathVariable("idSto") Long idStock){
        return stocksService.AffectProductToSupplies(idPro,quantity,idStock);
    }

    @GetMapping("getNbProducts/{id}")
    public Long getNbProductsInStock(@PathVariable Long id){
        return stocksService.getNbProductsInStock(id);
    }

    @GetMapping("getSumProSize/{id}")
    public Double getSumSizeOfProducts(@PathVariable Long id){
        return stocksService.getSumSizeOfProducts(id);
    }

    @PutMapping("withdraw/{id}")
    void withdrawStock(@RequestBody Double quantity,@PathVariable("id") Long idstock){
        stocksService.withdrawStock(quantity, idstock);
    }

    @GetMapping("getProdForStock/{id}")
    public Set<Product> getProductsForStock(@PathVariable("id") Long idStock){
        return  stocksService.getProductsForStock(idStock);
    }

}
