package com.devionics.co.Controller;

import com.devionics.co.DAO.CartDAO;
import com.devionics.co.DAO.ProductDAO;
import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.CartItem;
import com.devionics.co.Entity.Customer;
import com.devionics.co.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController
{
  @Autowired
  CartDAO cartDAO;

  @Autowired
  ProductDAO productDAO;

  private Cart cart = new Cart();


  @GetMapping()
  @ResponseBody
  public String getProductCart(@RequestParam(value = "customerId", required = true) long id)
  {
    try
    {
      Cart newCart = new Cart();
      Customer customer = cartDAO.getCustomerById(id);
      Cart cartByCustomerId = cartDAO.getCartByCustomerId(id);
      if (cartByCustomerId != null)
      {
        cart = cartByCustomerId;
      }
      else
      {
        newCart.setState("open");
        newCart.setCustomer(customer);
        newCart.setDate(new Date().toString());
        cart = cartDAO.createCart(newCart);
      }
      return cart.toString();
    }
    catch (Exception e)
    {
      return "Customer or Cart Not Found";
    }
  }

  @PostMapping(value = "/add")
  @ResponseBody
  public String addProductToCart(@RequestParam("quantity") int quantity, @RequestParam("productId") Long productId)
  {
    Product productById = productDAO.getProductById(productId);
    List<CartItem> cartItemlist = cart.getCartItem();
    CartItem cartItem = new CartItem();
    cartItem.setProduct(productById);
    cartItem.setQuantity(quantity);
    cartItemlist.add(cartItem);
    cart.setCartItem(cartItemlist);
    cartDAO.saveCartItem(cartItem);
    cartDAO.createCart(cart);
    return cart.toString();
  }

  @PutMapping(value = "/update")
  @ResponseBody
  public String updateProductToCart(@RequestParam("quantity") int quantity, @RequestParam("productId") Long productId)
  {
    List<CartItem> cartItemlist = cart.getCartItem();
    for (int i = 0; i < cartItemlist.size(); i++)
    {
      if (cartItemlist.get(i).getProduct().getId() == productId)
      {
        CartItem cartItem = cartItemlist.get(i);
        cartItem.setQuantity(quantity);
        cartDAO.saveCartItem(cartItemlist.get(i));
      }
    }
    return cart.toString();
  }

  @DeleteMapping(value = "/delete")
  @ResponseBody
  public String deleteProductToCart(@RequestParam("productId") Long productId)
  {
    List<CartItem> cartItemlist = cart.getCartItem();
    for (int i = 0; i < cartItemlist.size(); i++)
    {
      if (cartItemlist.get(i).getProduct().getId() == productId)
      {
        cartDAO.deleteCartItem(cartItemlist.get(i));
        cartItemlist.remove(i);
      }
    }
    cart.setCartItem(cartItemlist);
    return cart.toString();
  }
}
