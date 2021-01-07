package com.felypeganzert.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felypeganzert.dsdeliver.dto.ProductDTO;
import com.felypeganzert.dsdeliver.entities.Product;
import com.felypeganzert.dsdeliver.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	//@Autowired is not needed if we use lombok @RequiredArgsConstructor
	private final ProductRepository repository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
}
