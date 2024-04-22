package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    @Query("SELECT c FROM Categories c WHERE c.categoryName = ?1")
    Categories findCategoriesByName(String categoryName);
}
