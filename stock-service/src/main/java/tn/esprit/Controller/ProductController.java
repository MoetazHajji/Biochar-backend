package tn.esprit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Offer;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.IOfferService;
import tn.esprit.Interface.IProductService;
import tn.esprit.Interface.IScrapperService;
import tn.esprit.Service.ScrapperService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Tag(name = "Product Management")
@RequestMapping("/product")
public class ProductController {
    IProductService productService;
    ScrapperService scrapperService;
    IOfferService offerService;
    @Operation(description = "Create new product")
    @PostMapping("add")
    public Product addProduct(@RequestPart Product product,@RequestPart MultipartFile image){
        return productService.addProduct(product,image);
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
    @GetMapping("getProduct/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @Operation(description = "Retreive all products")
    @GetMapping("getAllProducts")
    public Set<Product> getAllProduct(){
        return productService.getProductList();
    }

    @GetMapping("getMostOrdredProducts")
    public List<Product> getMostOrderedProduct(){
        return productService.getMostOrderedProduct();
    }
    @GetMapping("getPrFromUrl")
    public List<Product> getPrFromUrl(){
        return scrapperService.run();
    }

    @GetMapping("getOfferForProduct/{id}")
    public List<Offer> getOffersForProduct(@PathVariable("id") Long idProd){
        return offerService.getOffersForProduct(idProd);
    }


}
