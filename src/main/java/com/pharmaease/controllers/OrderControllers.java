package com.pharmaease.controllers;

import com.pharmaease.dto.OrderDto;
import com.pharmaease.entity.Order;
import com.pharmaease.services.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderControllers {
	
	  @Autowired
	    private OrderService orderService;

	    @PostMapping
	    public ResponseEntity<Order> placeOrder(@RequestBody OrderDto orderDto) {
	        Order order = orderService.placeOrder(orderDto);
	        return new ResponseEntity<>(order, HttpStatus.CREATED);
	    }

	    @GetMapping("/{email}")
	    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String email) {
	        List<Order> orderHistory = orderService.getOrderHistory(email);
	        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
	    }


}
