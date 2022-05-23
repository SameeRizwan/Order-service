package com.devionics.co.Repository;

import com.devionics.co.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>
{
  @Query("Select c from Product c where c.type =:type")
  Page<Product> getProductByType(String type, Pageable pageable);

  @Query("Select c from Product c where c.color =:color")
  Page<Product> getProductByColor(String color, Pageable pageable);

  @Query("Select c from Product c where c.brand =:brand")
  Page<Product>  getProductByBrand(String brand, Pageable pageable);
}
