package com.jslet.sample.order;

import java.util.Date;
import java.util.List;

import com.jslet.dataset.record.AbstractStaticRecord;

public class Order extends AbstractStaticRecord {

	private static final long serialVersionUID = 4845026042321320733L;

	private String orderNo;

	private Date orderDate;

	private String customer;

	private String paymentTerm;

	private List<OrderItem> items;

	/**
	 * 获取[orderNo]
	 *
	 * @return orderNo [orderNo]
	 */
	public final String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置[orderNo]
	 *
	 * @param orderNo [orderNo]
	 */
	public final void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取[orderDate]
	 *
	 * @return orderDate [orderDate]
	 */
	public final Date getOrderDate() {
		return orderDate;
	}

	/**
	 * 设置[orderDate]
	 *
	 * @param orderDate [orderDate]
	 */
	public final void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 获取[customer]
	 *
	 * @return customer [customer]
	 */
	public final String getCustomer() {
		return customer;
	}

	/**
	 * 设置[customer]
	 *
	 * @param customer [customer]
	 */
	public final void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * 获取[paymentTerm]
	 *
	 * @return paymentTerm [paymentTerm]
	 */
	public final String getPaymentTerm() {
		return paymentTerm;
	}

	/**
	 * 设置[paymentTerm]
	 *
	 * @param paymentTerm [paymentTerm]
	 */
	public final void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	/**
	 * 获取[items]
	 *
	 * @return items [items]
	 */
	public final List<OrderItem> getItems() {
		return items;
	}

	/**
	 * 设置[items]
	 *
	 * @param items [items]
	 */
	public final void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
