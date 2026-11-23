package com.abhirami.orderhub.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	boolean existsBySku(String sku);
	Product findById(Long id);
}
