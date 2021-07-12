package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long id;
	private String name;
	private double value;

	public Item(String name, double value) {
		this.setName(name);
		this.setValue(value);
	}

	public Item(Long id, String name, double value) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value2) {
		this.value = value2;
	}

	@Override
	public String toString() {
		return "id:" + id + " name:" + name + " value:" + value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
		

}
