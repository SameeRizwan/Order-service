package com.devionics.co.Repository;

import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
  @Query("Select c from Cart c where c.customer.id = :id and c.state='open'")
  Cart getCartByCustomerId(Long id);
}
