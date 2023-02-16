package tn.esprit.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Adress;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.IProductService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    IProductService productService;

    @PostMapping("add")
    public Product addAdress(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @PutMapping("modify")
    public Product modifyAdress(@RequestBody Product product){
        return productService.modifyProduct(product);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
    @GetMapping("getAdress/{id}")
    public Product getAdressById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @GetMapping("getAllAdress")
    public List<Product> getAdressById(){
        return productService.getProductList();
    }
}
