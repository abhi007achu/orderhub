package com.abhirami.orderhub.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/products")
public class ProductController {

	
	private final ProductService productService;
	
	public ProductController (ProductService service) {
		this.productService=service;
	}
	
	@GetMapping
	public List<ProductResponse>  getProductList(){
		return productService.list();
	}
	
	@GetMapping("/{id}")
	public ProductResponse getProduct(@PathVariable Long id)
	{
		return productService.get(id);
	}
	
	@PostMapping("/{id}")
	public ProductResponse createNewProduct(@Valid @RequestBody ProductRequest request)
	{
		return productService.create(request);
	}
	
	@PutMapping("/{id}")
	public ProductResponse updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequest request)
	{
		return productService.update(id, request);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id)
	{
		productService.delete(id);
	}
}
