
package com.bankrate.common;

/** 
  * @Description: lop nhan xu ly du lieu model
  * @author:truonglt2
  * @since:Feb 7, 2014 3:52:48 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class ModelEvent {
	private ActionEvent actionEvent;
	Object modelData;
	int modelCode;
	String modelMessage;
	
	public void setActionEvent(ActionEvent actionEvent) {
		this.actionEvent = actionEvent;
	}
	public ActionEvent getActionEvent() {
		return actionEvent;
	}
	public Object getModelData() {
		return modelData;
	}
	public void setModelData(Object modelData) {
		this.modelData = modelData;
	}
	public int getModelCode() {
		return modelCode;
	}
	public void setModelCode(int modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelMessage() {
		return modelMessage;
	}
	public void setModelMessage(String modelMessage) {
		this.modelMessage = modelMessage;
	}
}
