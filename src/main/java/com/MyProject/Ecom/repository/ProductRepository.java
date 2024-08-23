package com.MyProject.Ecom.repository;

import com.MyProject.Ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContaining(String name);
}
