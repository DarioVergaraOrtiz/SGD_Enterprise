package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uce.edu.ec.SDG_Enterprise.Model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameAndMaterial(String name, String material);

    List<Product> findByMaterial(String material);

    Product findByid(Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.process WHERE p.id = :productId")
    Optional<Product> findByIdWithProcesses(@Param("productId") Long productId);

}
