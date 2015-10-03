package com.bankrate.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
  * @Description: Validator dinh dang email
  * @author:truonglt2
  * @since:Feb 7, 2014 5:24:44 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class EmailValidator {
	private Pattern pattern;
	  private Matcher matcher;

	  private static final String EMAIL_PATTERN = 
                 "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	  public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }

	  /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public boolean validate(final String hex){

		  matcher = pattern.matcher(hex);
		  return matcher.matches();

	  }

}
