package com.devionics.co.Controller;


import com.devionics.co.DAO.CartDAO;
import com.devionics.co.DAO.OrderDAO;
import com.devionics.co.Entity.Cart;
import com.devionics.co.Entity.CartItem;
import com.devionics.co.Entity.Order;
import com.devionics.co.Entity.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController
{
  @Autowired
  OrderDAO orderDAO;

  @Autowired
  CartDAO cartDAO;

  @PostMapping(value = "/add")
  @ResponseBody
  public String placeOrder(@RequestParam("cartId") Long cartId, @RequestParam("delivery") String delivery)
  {
    try
    {
      Cart cart = cartDAO.getCartById(cartId);
      Order order = new Order();
      order.setCart(cart);
      order.setDelivery(delivery);
      orderDAO.placeOrder(cart, order);
      return "Success";
    }
    catch (Exception e)
    {
      return e.getMessage();

    }
  }
}
