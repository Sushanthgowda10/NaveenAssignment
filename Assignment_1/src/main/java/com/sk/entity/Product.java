package com.sk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Product")
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 20)
	private Long product_id;

	@Column(length = 25)
	private String name;

	@Column(length = 25)
	private String category;

	private Double price;

	public Product(Long product_id) {
		super();
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Product Id = " + product_id + ", Name = " + name + ", Catagory = " + category + ", Price = " + price;
	}

}
