package com.sk.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.entity.Customer;
import com.sk.entity.Order;
import com.sk.entity.Product;
import com.sk.repo.ConstomerRepo;
import com.sk.repo.OrderRepo;
import com.sk.repo.ProductRepo;

@Service
public class ShoppingService {

	@Autowired
	private ConstomerRepo Crepo;

	@Autowired
	private OrderRepo Orepo;

	@Autowired
	private ProductRepo Prepo;

	public String insert(Order order) {
		List<Product> Plist = order.getProducts();
		Optional<Customer> Id = Crepo.findById(order.getCustomer().getId());
		if (Id.isPresent()) {
			order.setCustomer(Id.get());
			order.setDeliveryDate(LocalDate.now().plusDays(2));
			order.setOrderDate(LocalDate.now());
			List<Product> listOfProducts = new ArrayList<Product>();
			Plist.forEach(i -> listOfProducts.add(Prepo.findById(i.getProduct_id()).get()));
			order.setProducts(listOfProducts);
			Orepo.save(order);
			return "Inserted succesfully";
		}
		return "Not inserted";
	}

	public List<Product> productList() {
		return Prepo.findAll();
	}

	public List<Order> orderList() {
		return Orepo.findAll();
	}

}
