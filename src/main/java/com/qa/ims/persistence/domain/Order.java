package com.qa.ims.persistence.domain;

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

	public Order(Long customer_id, Long item_id, String first_name, String lastname, String item_name) {
		super();
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		return result;
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
		if (getFirst_name() == null) {
			if (other.getFirst_name() != null)
			return false;
		} else if (!getFirst_name().equals(other.getFirst_name()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (getLastname() == null) {
			if (other.getLastname() != null)
			return false;
		} else if (!getLastname().equals(other.getLastname()))
			return false;
		if (getItem_name() == null) {
			if (other.getItem_name() != null)
			return false;
		} else if (!getItem_name().equals(other.getItem_name()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", item_id=" + item_id + ", first_name="
				+ first_name + ", lastname=" + lastname + ", item_name=" + item_name + "]";
	}

}
