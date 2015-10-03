package com.bankrate.dto;

import java.io.Serializable;

/**
 * @Description: lop book
 * @author:truonglt2
 * @since:Feb 7, 2014 4:13:08 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
@SuppressWarnings("serial")
public class Book implements Serializable {
	private String idBook;
	private String title;
	private String description;
	private String urlImage;
	private String price;
	private String author;
	private String idCatagory;
	private String rate;

	/**
	 * @return the idBook
	 */
	public String getIdBook() {
		return idBook;
	}

	/**
	 * @param idBook
	 *            the idBook to set
	 */
	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the urlImage
	 */
	public String getUrlImage() {
		return urlImage;
	}

	/**
	 * @param urlImage
	 *            the urlImage to set
	 */
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the idCatagory
	 */
	public String getIdCatagory() {
		return idCatagory;
	}

	/**
	 * @param idCatagory
	 *            the idCatagory to set
	 */
	public void setIdCatagory(String idCatagory) {
		this.idCatagory = idCatagory;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
}