package com.devionics.co.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product
{

  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "color")
  private String color;

  @Column(name = "brand")
  private String brand;

  @Column(name = "price")
  private Double price;

  @Column(name = "description")
  private String description;

  @Override
  public String toString()
  {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", color='" + color + '\'' +
            ", brand='" + brand + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            '}';
  }
}
