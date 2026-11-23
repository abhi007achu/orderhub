package com.abhirami.orderhub.product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String sku, String name, BigDecimal price, int stock) {
	
	public static ProductResponse from(Product p) {
		return new ProductResponse(p.getId(), p.getSku(), p.getName(), p.getPrice(), p.getStock());
	}
}
