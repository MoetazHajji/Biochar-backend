package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.Product;

import java.util.List;
import java.util.Set;

@Service
public interface IProductService {
    Product addProduct(Product product);
    Product modifyProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    Set<Product> getProductList();
    Product AffectProductToCommand(Long idPro,Long idCommand);

    Product addProductToStock(Long id, Long quantity);

    List<Product>  getMostOrderedProduct();


}
