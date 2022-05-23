package com.devionics.co.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Order")
public class Order
{

  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @OneToOne
  private Cart cart;

  @Column(name = "delivery")
  private String delivery;

  @Override
  public String toString()
  {
    return "Order{" +
            "id=" + id +
            ", cart=" + cart +
            ", delivery='" + delivery + '\'' +
            '}';
  }
}
