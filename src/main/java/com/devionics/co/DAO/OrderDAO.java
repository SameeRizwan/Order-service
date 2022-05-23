package com.devionics.co.DAO;

import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.Order;
import com.devionics.co.Entity.Product;
import com.devionics.co.Repository.CartRepository;
import com.devionics.co.Repository.OrderRepository;
import com.devionics.co.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderDAO
{
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  CartRepository cartRepository;

  public String placeOrder(Cart cart, Order order)
  {
    try
    {
      cartRepository.save(cart);
      orderRepository.save(order);
      return "Success";
    }
    catch (Exception e)
    {
      return "Error";
    }
  }


}