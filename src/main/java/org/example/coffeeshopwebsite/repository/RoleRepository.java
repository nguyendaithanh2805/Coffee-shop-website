package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT name FROM Role WHERE name = ?1")
    String findByName(String username);
}
