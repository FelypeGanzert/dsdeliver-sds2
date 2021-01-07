package com.felypeganzert.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felypeganzert.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
