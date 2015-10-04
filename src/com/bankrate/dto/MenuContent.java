package com.bankrate.dto;

import java.io.Serializable;

/**
 * @Description: lop menu content
 * @author:truonglt2
 * @since:Feb 7, 2014 4:13:46 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
@SuppressWarnings("serial")
public class MenuContent implements Serializable {

	private int idMenu;
	private String title;

	/**
	 * @return the idMenu
	 */
	public int getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu
	 *            the idMenu to set
	 */
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
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
