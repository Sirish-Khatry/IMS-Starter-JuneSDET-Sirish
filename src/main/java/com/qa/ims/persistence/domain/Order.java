package com.qa.ims.persistence.domain;


import java.util.Objects;

public class Order {

	private Long id;
	private Long customer_id;
	private Long item_id;
	private String first_name, lastname, item_name;
	
	public Order(Long id, Long customer_id, Long item_id) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
	}

	public Order(Long id, Long item_id) {
		super();
		this.id = id;
		this.item_id = item_id;
	}
	
	public Order(Long customer_id) {
		super();
		this.customer_id = customer_id;
	}
	
	public Order(Long id, Long customer_id, Long item_id, String first_name, String lastname, String item_name) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.first_name = first_name;
		this.lastname = lastname;
		this.item_name = item_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer_id, first_name, id, item_id, item_name, lastname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customer_id, other.customer_id) && Objects.equals(first_name, other.first_name)
				&& Objects.equals(id, other.id) && Objects.equals(item_id, other.item_id)
				&& Objects.equals(item_name, other.item_name) && Objects.equals(lastname, other.lastname);
	}


	


}

