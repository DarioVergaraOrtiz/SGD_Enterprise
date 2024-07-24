package uce.edu.ec.SDG_Enterprise.Sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public Product saveProduct(String name, String material, double price) {
        Product product = new Product();
        product.setName(name);
        product.setMaterial(material);
        product.setPrice(price);
        return productRepository.save(product);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
