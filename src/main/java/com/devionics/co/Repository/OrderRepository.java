package com.devionics.co.Repository;

import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{

}
