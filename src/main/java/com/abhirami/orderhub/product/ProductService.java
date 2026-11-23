package com.abhirami.orderhub.product;

import java.util.List;

public interface ProductService {
	
	List<ProductResponse> list();
	
	ProductResponse get(Long id);
	
	ProductResponse create(ProductRequest request);
	
	ProductResponse update(Long id,ProductRequest request);
	
	void delete(Long id);
	
}
