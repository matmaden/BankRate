package com.bankrate.controller;

import com.bankrate.common.ActionEvent;
import com.bankrate.common.ModelEvent;

/** 
  * @Description:lop abstract tao cac su kien controller
  * @author:truonglt2
  * @since:Feb 7, 2014 3:53:43 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public abstract class AbstractController {
	abstract public void handleViewEvent(ActionEvent e);
	abstract public void handleModelEvent(ModelEvent modelEvent);
	abstract public void handleErrorModelEvent(ModelEvent modelEvent);
    abstract public void handleSwitchActivity(ActionEvent e);
}
