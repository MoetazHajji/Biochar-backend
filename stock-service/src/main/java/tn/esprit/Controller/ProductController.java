package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.IProductService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Tag(name = "Product Management")
@RequestMapping("/product")
public class ProductController {
    IProductService productService;

    @Operation(description = "Create new product")
    @PostMapping("add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @Operation(description = "Modify product")
    @PutMapping("modify")
    public Product modifyProduct(@RequestBody Product product){
        return productService.modifyProduct(product);
    }

    @Operation(description = "Delete product")
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @Operation(description = "Retreive product by id")
    @GetMapping("getAdress/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @Operation(description = "Retreive all products")
    @GetMapping("getAllAdress")
    public Set<Product> getAllProduct(){
        return productService.getProductList();
    }



}
