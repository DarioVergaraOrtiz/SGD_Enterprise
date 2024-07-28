package uce.edu.ec.SDG_Enterprise.Sevice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import uce.edu.ec.SDG_Enterprise.Model.Process;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.IProductRepository;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.ProcessRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProcessRepository processRepository;

    public void saveProduct(String name, String material, double price) {
        if (!productRepository.existsByNameAndMaterial(name, material)) {
            Product product = new Product();
            product.setName(name);
            product.setMaterial(material);
            product.setPrice(price);
            productRepository.save(product);
        }
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Transactional
    public void addProcessToProduct(Long productId, Long processId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new EntityNotFoundException("Process not found"));

        product.getProcess().add(process);

        productRepository.save(product);
    }

}
