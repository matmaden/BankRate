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

	private String idMenu;
	private String title;
	private String index;
	private String level;
	private String content;
	private String idBook;

	/**
	 * @return the idMenu
	 */
	public String getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu
	 *            the idMenu to set
	 */
	public void setIdMenu(String idMenu) {
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

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

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
}
