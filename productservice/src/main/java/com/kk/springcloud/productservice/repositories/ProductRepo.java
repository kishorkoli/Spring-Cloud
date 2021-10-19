package com.kk.springcloud.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.springcloud.productservice.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
