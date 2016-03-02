package org.socius.jbpm.workitemhandler;

import java.util.List;

public class Order {
	
	private long orderid;
	private long customerid;
	
	private boolean creditCheck;
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isCreditCheck() {
		return creditCheck;
	}
	public void setCreditCheck(boolean creditCheck) {
		this.creditCheck = creditCheck;
	}
	private List<Product> product;

	
	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public long getCustomerid() {
		return customerid;
	}
	public long getOrderid() {
		return orderid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	

}
