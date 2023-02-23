package tn.esprit.Mappers;

import tn.esprit.Entity.Stock;
import tn.esprit.dto.StockDto;

import java.util.ArrayList;
import java.util.List;

public class StockMapper {
    public static StockDto mapToDo(Stock stock){
        List<String> prList = new ArrayList<>();
        stock.getProducts().forEach(product -> prList.add(product.getName_product()));
        StockDto stockDto=StockDto.builder()
                .id(stock.getId())
                .date(stock.getDate())
                .location(stock.getLocation())
                .storage(stock.getStorage())
                .free_storage(stock.getFree_storage())
                .used_storage(stock.getUsed_storage())
                .product_names(prList)
                .build();
        return stockDto;
    }
    public static Stock mapToEntity(StockDto stockDto){
        Stock stock=Stock.builder()
                .id(stockDto.getId())
                .date(stockDto.getDate())
                .location(stockDto.getLocation())
                .storage(stockDto.getStorage())
                .free_storage(stockDto.getFree_storage())
                .used_storage(stockDto.getUsed_storage())
                .build();

        return stock;
    }
}
