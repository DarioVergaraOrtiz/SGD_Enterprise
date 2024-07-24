package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

  //  @Query("SELECT p FROM Product p JOIN FETCH p.processes WHERE p.id = :productId")
  //  Optional<Product> findByIdWithProcesses(@Param("productId") Long productId);


}
