package com.bankrate.dto;

import java.io.Serializable;

/**
 * @Description: lop catagory
 * @author:truonglt2
 * @since:Feb 7, 2014 4:13:27 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
@SuppressWarnings("serial")
public class Catagory implements Serializable {

	private String idCatagory;
	private String title;

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
}
