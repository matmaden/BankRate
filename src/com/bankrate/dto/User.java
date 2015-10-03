package com.bankrate.dto;

/**
 * @author:truonglt2
 * @since:Jan 21, 2014 4:43:57 PM
 * @Description: lop user
 */
public class User {

	private String id;
	private String displayname;
	private String password;
	private String email;
	private String mUrlAvatar;

	/**
	 * @author: truonglt2
	 */
	public User() {
		setId("");
		setDisplayname("");
		setPassword("");
		setEmail("");
	}

	public User(String Id, String email, String pass, String displayName) {
		this.id = Id;
		this.email = email;
		this.password = pass;
		this.displayname = displayName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the displayname
	 */
	public String getDisplayname() {
		return displayname;
	}

	/**
	 * @param displayname
	 *            the displayname to set
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mUrlAvatar
	 */
	public String getmUrlAvatar() {
		return mUrlAvatar;
	}

	/**
	 * @param mUrlAvatar
	 *            the mUrlAvatar to set
	 */
	public void setmUrlAvatar(String mUrlAvatar) {
		this.mUrlAvatar = mUrlAvatar;
	}

}
