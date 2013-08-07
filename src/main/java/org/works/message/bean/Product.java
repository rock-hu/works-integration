package org.works.message.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.camel.dataformat.bindy.annotation.Link;

@Link
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7340146673819015789L;

	private String productCode;

	private String shortName;

	private String productName;

	private BigDecimal unitPrice;

	private float discount;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

}
