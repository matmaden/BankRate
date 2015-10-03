package com.bankrate.common;


/** 
  * @Description: tao lop lang nghe cac event cho controller
  * @author:truonglt2
  * @since:Feb 7, 2014 3:51:42 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class ActionEvent {
	 public int action;
	 public Object modelData;
	 public Object viewData;
	 public Object sender;
	 /**
	*  reset gia tri
	*  @author: truonglt2
	*  @return: void
	*  @throws: 
	*/
	public void reset(){
		 action = 0;
		 modelData = null;
		 viewData = null;
		 sender = null;
	 }
	 
}
