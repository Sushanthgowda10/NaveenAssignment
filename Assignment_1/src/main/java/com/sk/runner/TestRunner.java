package com.sk.runner;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sk.entity.Order;
import com.sk.entity.Product;
import com.sk.repo.ProductRepo;
import com.sk.service.ShoppingService;

@Component
public class TestRunner implements CommandLineRunner {
	@Autowired
	private ShoppingService service;

	@Autowired
	private ProductRepo pRepo;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("=========");

		System.out.println("Obtain a list of products belongs to category Books with price > 100");
		List<Product> listOfProduct1 = service.productList();
		System.out.println(listOfProduct1);
		listOfProduct1.stream().filter(ls -> ls.getCategory().equalsIgnoreCase("books") && ls.getPrice() > 100)
				.collect(Collectors.toList()).forEach(c -> System.out.println(c));

		System.out.println("=========");
		
		System.out.println("Obtain a list of order with products belong to category Baby");
		List<Order> listOfOrders = service.orderList();
       List<Order> collect = listOfOrders.stream()
       .filter(order -> order.getProducts().stream()
        .anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
        .collect(Collectors.toList());
       System.out.println(collect);

		

		System.out.println("=========");

		System.out.println(" Obtain a list of product with category Toys and then apply 10% discount ");
		List<Product> listOfProduct2 = service.productList();
		List<Product> collect2 = listOfProduct2.stream().filter(l -> l.getCategory().equalsIgnoreCase("Toys"))
				.map(p -> {
					p.setPrice(p.getPrice() - (p.getPrice() * 0.10));
					return p;
				}).collect(Collectors.toList());
		collect2.forEach(p -> System.out.println(p.toString()));
		
		System.out.println("=========");

		System.out.println("Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021");
		List<Order> listOfOrders4 = service.orderList();
		List<Order> collect4 = listOfOrders4.stream()
				.filter(c -> c.getCustomer().getTier() == 2 && c.getOrderDate().isAfter(LocalDate.of(2021, 02, 01))
						&& c.getDeliveryDate().isBefore(LocalDate.of(2021, 04, 01)))
				.collect(Collectors.toList());
		System.out.println(collect4);
		
		System.out.println("=========");

		System.out.println("Get the cheapest products of Books category");
		List<Product> listOfProduct3 = service.productList();
		Optional<Product> filter = listOfProduct3.stream().filter(p -> p.getCategory().equalsIgnoreCase("Books"))
				.min(Comparator.comparing(Product::getPrice));
		System.out.println(filter);
		
		System.out.println("=========");

		System.out.println("Get the 3 most recent placed order");
		List<Order> listOfOrders2 = service.orderList();
		List<Order> collect3 = listOfOrders2.stream().sorted(Comparator.comparing(Order::getOrderDate).reversed())
				.limit(3).collect(Collectors.toList());
		collect3.forEach((p) -> {
			System.out.println(p.toString());
		});
		
		System.out.println("=========");

		System.out.println(" Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list"
				);
		List<Order> listOfOrders7 = service.orderList();
		listOfOrders7.stream().filter(x -> x.getOrderDate().isEqual(LocalDate.parse("2021-03-15")))
				.map(p -> p.getProducts()).forEach(System.out::println);
		
		
		System.out.println("=========");

		System.out.println("Calculate total lump sum of all orders placed in Feb 2021");
		List<Order> listOfOrders8 = service.orderList();
		long count3 = listOfOrders8.stream().filter(f -> f.getOrderDate().isEqual(LocalDate.of(2021, 02, 01)))
				.map(m -> m.getOrder_id()).collect(Collectors.toList()).stream().count();
		System.out.println(count3);
		
		
		System.out.println("=========");

		System.out.println("Calculate order average payment placed on 14-Mar-2021");
				
		DoubleSummaryStatistics collect9 = service.orderList().stream()
				.filter(x -> x.getOrderDate().isEqual(LocalDate.parse("2021-03-14")))
				.flatMap(i -> i.getProducts().stream()).mapToDouble(P -> P.getPrice()).summaryStatistics();
		System.out.println("Avg payments of orders in 14 March 2021 : " + collect9.getAverage());
		
		
		System.out.println("=========");

		System.out.println(" Obtain a collection of statistic figures i.e., sum, average, max, min, count for all products of category Books");
				
				
		List<Product> productList = service.productList();
		DoubleSummaryStatistics stat = productList.stream().filter(ls -> ls.getCategory().equalsIgnoreCase("book"))
				.mapToDouble(x -> x.getPrice()).summaryStatistics();
		System.out.println("Sum : " + stat.getSum());
		System.out.println("Avg : " + stat.getAverage());
		System.out.println("Max : " + stat.getMax());
		System.out.println("Min : " + stat.getMin());
		System.out.println("Count : " + stat.getCount());
		
		
		System.out.println("=========");

		System.out.println("Obtain a data map with order id and order’s product count");
		
		service.orderList().stream().collect(Collectors.toMap(p -> p.getOrder_id(), q -> q.getProducts().size()))
				.entrySet().stream().toList().forEach(System.out::println);
		
		System.out.println("=========");

		System.out.println("Produce a data map with order records grouped by customer");

		service.orderList().stream().collect(Collectors.groupingBy(Order::getCustomer)).entrySet().stream().toList()
				.forEach(System.out::println);
		
		
		System.out.println("=========");

		System.out.println("Produce a data map with order record and product total sum");
		service.orderList().stream()
				.collect(Collectors.toMap(i -> i, o -> o.getProducts().stream().mapToDouble(i -> i.getPrice()).sum()))
				.entrySet().stream().toList().forEach(System.out::println);
		
		System.out.println("=========");

		System.out.println("Obtain a data map with list of product name by category");

		service.productList().stream().collect(Collectors.groupingBy(i -> i.getCategory())).entrySet().stream()

				.forEach(System.out::println);
		
		
		System.out.println("=========");

		System.out.println("Get the most expensive product by category");

		productList.stream()

				.collect(Collectors.groupingBy(Product::getCategory,

						Collectors.maxBy(Comparator.comparing(Product::getPrice))))

				.forEach((category, product) -> System.out.println(category + " :: " + product.get().getPrice()));
	}

}
