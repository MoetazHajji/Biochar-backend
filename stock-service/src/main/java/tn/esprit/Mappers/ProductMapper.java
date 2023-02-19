package tn.esprit.Mappers;

import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Product;
import tn.esprit.dto.AdressDto;
import tn.esprit.dto.ProductDto;

public class ProductMapper {
    public static ProductDto mapToDo(Product product){
        ProductDto productDto=ProductDto.builder()
                .id(product.getId())
                .name_product(product.getName_product())
                .type_product(product.getType_product())
                .reference(product.getReference())
                .designation(product.getDesignation())
                .build();
        return productDto;
    }
    public static Product mapToEntity(ProductDto productDto){
        Product product=Product.builder()
                .id(productDto.getId())
                .name_product(productDto.getName_product())
                .type_product(productDto.getType_product())
                .build();
        return product;
    }
}
