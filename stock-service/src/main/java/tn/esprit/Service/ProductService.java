package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Command;
import tn.esprit.Entity.Product;
import tn.esprit.Entity.Stock;
import tn.esprit.Interface.IProductService;
import tn.esprit.Repository.ICommandRepository;
import tn.esprit.Repository.IProductRepository;
import tn.esprit.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    IProductRepository productRepository;
    ICommandRepository commandRepository;
    IStockRepository stockRepository;


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean ifProductExist(String reference){
        List<Product> p = productRepository.findProduc(reference);
        if(p.size()==0){
            return false;
        }else
            return true;
    }

    @Override
    public Product modifyProduct(Product product) {
            productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductList() {
        List<Product> productList=new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList ;
    }

    @Override
    public Product AffectProductToCommand(Long idPro,Long idCommand) {
        Product product=productRepository.findById(idPro).orElse(null);
        Command command=commandRepository.findById(idCommand).orElse(null);

            productRepository.save(product);

        return product;
    }

    @Override
    public Product addProductToStock(Long idPro, Long quantity) {
        Product product=productRepository.findById(idPro).orElse(null);
        return null;
    }


}
