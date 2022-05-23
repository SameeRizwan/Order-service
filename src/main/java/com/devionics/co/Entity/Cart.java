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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Cart")
public class Cart
{

  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @OneToMany
  private List<CartItem> cartItem = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "date")
  private String date;

  @Column(name = "state")
  private String state;

  @Override
  public String toString()
  {
    return "Cart{" +
            "id=" + id +
            ", cartItem=" + cartItem +
            ", customer=" + customer +
            ", date='" + date + '\'' +
            '}';
  }
}
