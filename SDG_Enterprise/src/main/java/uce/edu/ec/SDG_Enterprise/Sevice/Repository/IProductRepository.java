package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameAndMaterial(String name, String material);
    List<Product> findByMaterial(String material);
    Product findByid(Long id);
    boolean existsByNameAndMaterial(String name, String material);

}
