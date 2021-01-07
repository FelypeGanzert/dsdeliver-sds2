package com.felypeganzert.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felypeganzert.dsdeliver.dto.OrderDTO;
import com.felypeganzert.dsdeliver.entities.Order;
import com.felypeganzert.dsdeliver.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	//@Autowired is not needed if we use lombok @RequiredArgsConstructor
	private final OrderRepository repository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
}
