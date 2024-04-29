package com.pharmaease.entity;



import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    @ManyToMany
	    @JoinTable(
	            name = "order_products",
	            joinColumns = @JoinColumn(name = "order_id"),
	            inverseJoinColumns = @JoinColumn(name = "product_id"))
	    private List<Product> products;

	    private Double totalPrice;


		private String shippingAddress;

	    private String status;

	    private String paymentStatus;
	    
	 
	    public Order() {
	    }

	    public Order(User user, List<Product> products, Double totalPrice, String shippingAddress, String status, String paymentStatus) {
	        this.user = user;
	        this.products = products;
	        this.totalPrice = totalPrice;
	        this.shippingAddress = shippingAddress;
	        this.status = status;
	        this.paymentStatus = paymentStatus;
	    }
	 
	
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		public Double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}



}
