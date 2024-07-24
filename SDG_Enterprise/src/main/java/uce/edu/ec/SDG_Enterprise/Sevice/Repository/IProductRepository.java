package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.SDG_Enterprise.Model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
