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

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product saveProduct(String name, String material, double price) {
        Product product = new Product();
        product.setName(name);
        product.setMaterial(material);
        product.setPrice(price);
        associateProductWithProcesses(product);
        return productRepository.save(product);
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    public void addProcessToProduct(Long productId, Long processId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new EntityNotFoundException("Process not found"));

        product.getProcess().add(process);

        productRepository.save(product);
    }

    public void updateProductPrice(String name, String material, double newPrice) {
        List<Product> products = productRepository.findByNameAndMaterial(name, material);

        if (!products.isEmpty()) {
            // Assuming you want to update the price of the first matched product
            Product product = products.get(0);
            product.setPrice(newPrice);
            productRepository.save(product);
            System.out.println("Price actalizado con exito .");
        } else {
            System.out.println("Producto no encontrado por name y material.");
        }
    }

    public List<Product> getProductsByMaterial(String material) {
        return productRepository.findByMaterial(material);
    }

    @Transactional
    public void addProcessToProductsByMaterial(String material, Long processId) {
        List<Product> products = productRepository.findByMaterial(material);

        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found with material: " + material);
        }

        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new EntityNotFoundException("Process not found"));

        for (Product product : products) {
            product.getProcess().add(process); // Assuming getProcesses() returns a List<Process>
            productRepository.save(product);
        }
    }

    public void associateProductWithProcesses(Product product) {
        List<Process> processes = processRepository.findByNameMaterial(product.getMaterial());

        for (Process process : processes) {
            product.getProcess().add(process);
            process.getProducts().add(product);
        }

        productRepository.save(product);
        processRepository.saveAll(processes);
    }

    public Product getProductById(Long id) {
        return productRepository.findByid(id);
    }

    public Product findByIdWithProcesses(Long productId) {
        return productRepository.findByIdWithProcesses(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
