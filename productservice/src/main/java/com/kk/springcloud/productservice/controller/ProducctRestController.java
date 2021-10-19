package com.kk.springcloud.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.springcloud.productservice.models.Coupon;
import com.kk.springcloud.productservice.models.Product;
import com.kk.springcloud.productservice.repositories.ProductRepo;
import com.kk.springcloud.productservice.restclient.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping(value= "/productapi")
public class ProducctRestController {
	
	@Autowired
	CouponClient couponClient;
	
	@Autowired
	private ProductRepo repo;

	@PostMapping("/products")
	@Retry(name="product-api", fallbackMethod="handleError")
	public Product create(@RequestBody Product product) {	
		Coupon coupon= couponClient.getCoupon(product.getCouponcode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);		
	}
	
	public Product handleError(Product product, Exception e) {
		System.out.println("### Inside handle error");
		return product;	
		
	}
}
