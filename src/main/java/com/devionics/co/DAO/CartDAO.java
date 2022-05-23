package com.devionics.co.DAO;

import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.CartItem;
import com.devionics.co.Entity.Customer;
import com.devionics.co.Entity.Product;
import com.devionics.co.Repository.CartItemRepository;
import com.devionics.co.Repository.CartRepository;
import com.devionics.co.Repository.CustomerRepository;
import com.devionics.co.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CartDAO
{
  @Autowired
  CartRepository cartRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CartItemRepository cartItemRepository;

  public Customer getCustomerById(Long id){
    return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id:" + id));
  }


  public Cart getCartByCustomerId(Long id)
  {
    Cart cartByCustomerId = cartRepository.getCartByCustomerId(id);
    return cartByCustomerId;
  }

  public Cart getCartById(Long id)
  {
    Cart cartById = cartRepository.getById(id);
    return cartById;
  }

  public Cart createCart(Cart cart)
  {
    return cartRepository.save(cart);
  }

  public void saveCartItem(CartItem cartItem)
  {
    cartItemRepository.save(cartItem);
  }

  public void deleteCartItem(CartItem cartItem)
  {
    cartItemRepository.delete(cartItem);
  }

}