package com.felypeganzert.dsdeliver.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felypeganzert.dsdeliver.dto.OrderDTO;
import com.felypeganzert.dsdeliver.dto.ProductDTO;
import com.felypeganzert.dsdeliver.entities.Order;
import com.felypeganzert.dsdeliver.entities.OrderStatus;
import com.felypeganzert.dsdeliver.entities.Product;
import com.felypeganzert.dsdeliver.repositories.OrderRepository;
import com.felypeganzert.dsdeliver.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	//@Autowired is not needed if we use lombok @RequiredArgsConstructor
	private final OrderRepository repository;
	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(),
				dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for(ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}
