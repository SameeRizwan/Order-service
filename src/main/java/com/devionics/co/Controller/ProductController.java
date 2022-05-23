package com.devionics.co.Controller;

import com.devionics.co.DAO.ProductDAO;
import com.devionics.co.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

import static com.devionics.co.Pagination.Paging.CURRENT_PAGE;
import static com.devionics.co.Pagination.Paging.PAGE_SIZE;

@Controller
@RequestMapping(value = "/products")
public class ProductController
{

  @Autowired
  ProductDAO productDAO;


  @GetMapping
  @ResponseBody
  public String getAllProducts(@RequestParam(value = "pageNumber", required = false, defaultValue = CURRENT_PAGE) int pageNumber,
                               @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE) int size)
  {
    Page<Product> productAllProduct = productDAO.getProductAllProduct(pageNumber, size);
    return convertInfoToString(productAllProduct);
  }

  @GetMapping(value = "/color")
  @ResponseBody
  public String getAllProductsByColor(@RequestParam(value = "color", required = true) String color, @RequestParam(value = "pageNumber", required = false, defaultValue = CURRENT_PAGE) int pageNumber,
                                      @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE) int size)
  {
    Page<Product> productAllProduct = productDAO.getProductByColor(pageNumber, size, color);
    return convertInfoToString(productAllProduct);
  }

  @GetMapping(value = "/type")
  @ResponseBody
  public String getAllProductsByType(@RequestParam(value = "pageNumber", required = false, defaultValue = CURRENT_PAGE) int pageNumber,
                                     @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE) int size, @RequestParam(value = "type", required = true) String type)
  {
    Page<Product> productAllProduct = productDAO.getProductByType(pageNumber, size, type);
    return convertInfoToString(productAllProduct);
  }

  @GetMapping(value = "/brand")
  @ResponseBody
  public String getAllProductsByBrand(@RequestParam(value = "pageNumber", required = false, defaultValue = CURRENT_PAGE) int pageNumber,
                                      @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE) int size, @RequestParam(value = "brand", required = true) String brand)
  {
    Page<Product> productAllProduct = productDAO.getProductByBrand(pageNumber, size, brand);
    return convertInfoToString(productAllProduct);
  }

  private String convertInfoToString(Page<Product> products)
  {
    List<Product> content = products.getContent();
    long totalElements = products.getTotalElements();
    int totalPages = products.getTotalPages();
    int page = products.getPageable().getPageNumber() + 1;
    String info = "Total Elements: " + totalElements + "  Total Page: " + totalPages + "  Page: " + page + "  Products: " + Arrays.toString(new List[]{content});
    return info;
  }
}
