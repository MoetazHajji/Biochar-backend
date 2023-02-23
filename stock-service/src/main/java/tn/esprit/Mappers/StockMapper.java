package tn.esprit.Mappers;

import tn.esprit.Entity.Stock;
import tn.esprit.dto.StockDto;

public class StockMapper {
    public static StockDto mapToDo(Stock stock){
        StockDto stockDto=StockDto.builder()
                .id(stock.getId())
                .date(stock.getDate())
                .location(stock.getLocation())
                .storage(stock.getStorage())
                .free_storage(stock.getFree_storage())
                .used_storage(stock.getUsed_storage())
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
