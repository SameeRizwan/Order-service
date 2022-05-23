package com.devionics.co.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CartItem")
public class CartItem
{

  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "quantity")
  private int quantity;

  @Override
  public String toString()
  {
    return "CartItem{" +
            "id=" + id +
            ", product=" + product +
            ", quantity=" + quantity +
            '}';
  }
}
