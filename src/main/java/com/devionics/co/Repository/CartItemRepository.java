package com.devionics.co.Repository;

import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>
{

}
