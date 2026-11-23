package com.abhirami.orderhub.product;

import java.util.List;

import org.hibernate.annotations.processing.Find;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhirami.orderhub.error.ConflictException;
import com.abhirami.orderhub.error.NotFoundException;



@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository repo)
	{
		this.productRepo=repo;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductResponse> list() {
		
		return productRepo.findAll().stream().map(ProductResponse::from).toList();
		
	}

	@Override
	@Transactional(readOnly = true)
	public ProductResponse get(Long id) {
		
		return ProductResponse.from(find(id));
	}


	@Override
	@Transactional
	public ProductResponse create(ProductRequest request) {
		
		if(productRepo.existsBySku(request.sku())) {
			throw new ConflictException("SKU already exists:"+ request.sku());
		}
		
		Product product= new Product();
		apply(product,request);
		return ProductResponse.from((productRepo.save(product)));
		
	}

	
	@Override
	@Transactional
	public ProductResponse update(Long id, ProductRequest request) {
		
		Product product= find(id);
		if(!product.getSku().equals(request.sku())&& productRepo.existsBySku(request.sku())) {
			throw new ConflictException("SKU already exists:"+ request.sku());
		}
		
		apply(product, request);
		return ProductResponse.from(product);
	}

	@Override
	public void delete(Long id) {
		
		productRepo.delete(find(id));
		
	}
	
	private Product find(Long id) {
		return productRepo.findById(id);
	}
	
	private void apply(Product product, ProductRequest request) {
		
		product.setSku(request.sku());
		product.setName(request.name());
		product.setPrice(request.price());
		product.setStock(request.stock());
		
	}

}
