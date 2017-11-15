/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.jslet.sample.order;

import com.jslet.dataset.record.AbstractStaticRecord;

public class OrderItem extends AbstractStaticRecord {
	private static final long serialVersionUID = 254842951080746172L;

	private Integer seqno;

	private String product;

	private Integer qty;

	private Double price;

	private Double amount;

	/**
	 * 获取[seqno]
	 *
	 * @return seqno [seqno]
	 */
	public final Integer getSeqno() {
		return seqno;
	}

	/**
	 * 设置[seqno]
	 *
	 * @param seqno [seqno]
	 */
	public final void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	/**
	 * 获取[product]
	 *
	 * @return product [product]
	 */
	public final String getProduct() {
		return product;
	}

	/**
	 * 设置[product]
	 *
	 * @param product [product]
	 */
	public final void setProduct(String product) {
		this.product = product;
	}

	/**
	 * 获取[qty]
	 *
	 * @return qty [qty]
	 */
	public final Integer getQty() {
		return qty;
	}

	/**
	 * 设置[qty]
	 *
	 * @param qty [qty]
	 */
	public final void setQty(Integer qty) {
		this.qty = qty;
	}

	/**
	 * 获取[price]
	 *
	 * @return price [price]
	 */
	public final Double getPrice() {
		return price;
	}

	/**
	 * 设置[price]
	 *
	 * @param price [price]
	 */
	public final void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 获取[amount]
	 *
	 * @return amount [amount]
	 */
	public final Double getAmount() {
		return amount;
	}

	/**
	 * 设置[amount]
	 *
	 * @param amount [amount]
	 */
	public final void setAmount(Double amount) {
		this.amount = amount;
	}

}
