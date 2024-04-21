package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
}
