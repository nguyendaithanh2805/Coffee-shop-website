package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
