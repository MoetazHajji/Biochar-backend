package tn.esprit.Interface;

import org.springframework.stereotype.Service;
import tn.esprit.Entity.Product;

import java.util.List;

@Service
public interface IProductService {
    Product addProduct(Product product);
    Product modifyProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getProductList();
    Product AffectProductToCommand(Long idPro,Long idCommand);

}
