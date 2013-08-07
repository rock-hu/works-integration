package org.works.message.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.Link;
import org.apache.camel.dataformat.bindy.annotation.LinkType;

@Link
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457571724664963229L;

	private String orderItemSequence;
	@Link(linkType = LinkType.OneToOne)
	private Product product;

	private Long quantity;

	private BigDecimal lumpSum;

	private BigDecimal preferentialSum;

	private Date createTime;

	public String getOrderItemSequence() {
		return orderItemSequence;
	}

	public void setOrderItemSequence(String orderItemSequence) {
		this.orderItemSequence = orderItemSequence;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getLumpSum() {
		return lumpSum;
	}

	public void setLumpSum(BigDecimal lumpSum) {
		this.lumpSum = lumpSum;
	}

	public BigDecimal getPreferentialSum() {
		return preferentialSum;
	}

	public void setPreferentialSum(BigDecimal preferentialSum) {
		this.preferentialSum = preferentialSum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
