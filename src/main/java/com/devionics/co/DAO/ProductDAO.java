package com.devionics.co.DAO;

import com.devionics.co.Entity.Product;
import com.devionics.co.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDAO
{
  @Autowired
  ProductRepository productRepository;


  public Product getProductById(long id)
  {
    return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id:" + id));
  }

  public Page<Product> getProductAllProduct(int pageNumber, int size)
  {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    return productRepository.findAll(request);
  }

  public Page<Product> getProductByType(int pageNumber, int size, String type)
  {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Product> productByType = productRepository.getProductByType(type, request);
    return productByType;
  }

  public Page<Product> getProductByColor(int pageNumber, int size, String color)
  {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Product> ProductByColor = productRepository.getProductByColor(color, request);
    return ProductByColor;
  }

  public Page<Product> getProductByBrand(int pageNumber, int size, String brand)
  {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Product> ProductByBrand = productRepository.getProductByBrand(brand, request);
    return ProductByBrand;
  }

}