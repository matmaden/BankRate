package com.bankrate.models;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;

import com.bankrate.R;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.Common;
import com.bankrate.common.ErrorConstants;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.database.ConstantsDatabase;
import com.bankrate.database.DatabaseHelp;
import com.bankrate.dto.Book;
import com.bankrate.dto.Catagory;
import com.bankrate.dto.MenuContent;

/**
 * @Description: lop model nhan cac request tu controler va xu ly data de tra ve
 *               cho controller
 * @author:truonglt2
 * @since:Feb 7, 2014 5:10:15 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class UserModel {
	protected static UserModel instance;

	protected UserModel() {
	}

	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}
		return instance;
	}

	/**
	 * nhan request data tu controller
	 * 
	 * @author: truonglt2
	 * @param e
	 * @return: void
	 * @throws:
	 */
	public void requestData(ActionEvent e) {
		ModelEvent model = new ModelEvent();
		model.setActionEvent(e);
		Activity base = null;
		if (e.sender instanceof Activity) {
			base = (Activity) e.sender;
		} else if (e.sender instanceof Fragment) {
			base = (Activity) ((Fragment) e.sender).getActivity();
		}
		switch (e.action) {
		// handle event get data list category
		case ActionEventConstant.GET_DATA_LIST_MENU: {
			try {
				ArrayList<MenuContent> arrMenu = new ArrayList<MenuContent>();
				MenuContent menu1 = new MenuContent();
				menu1.setIdMenu(Common.SCREEN_INFOR_RATE);
				menu1.setTitle("Thông tin lãi xuất");
				arrMenu.add(menu1);
				MenuContent menu2 = new MenuContent();
				menu2.setIdMenu(Common.SCREEN_INFOR_LOAN);
				menu2.setTitle("Thông tin tiền vay");
				arrMenu.add(menu2);
				
				model.setModelData(arrMenu);
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				UserController.getInstance().handleModelEvent(model);
			} catch (Exception ex) {
				model.setModelMessage(ex.getMessage());
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				UserController.getInstance().handleErrorModelEvent(model);
			}
			break;
		}
		// handle event get data list book of catagory
		case ActionEventConstant.GET_DATA_LIST_BOOK_OF_CATAGORY: {
			try {
				Bundle bundle = (Bundle) e.viewData;
				String idCatagory = bundle.getString("idCatagory");
				String query = ConstantsDatabase.queryAllTable
						+ ConstantsDatabase.BOOK_TABLE + " where idCatagory='"
						+ idCatagory + "' ;";
				Cursor result = DatabaseHelp.getInstance(base).selectQuery(
						query);
				ArrayList<Book> arrBook = getArrBook(result);
				DatabaseHelp.getInstance(base).close();
				model.setModelData(arrBook);
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				UserController.getInstance().handleModelEvent(model);
			} catch (Exception ex) {
				model.setModelMessage(ex.getMessage());
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				UserController.getInstance().handleErrorModelEvent(model);
			}
			break;
		}
		// handle event get data list menu and content of book
		case ActionEventConstant.GET_DATA_LIST_MENU_OF_BOOK: {
			try {

				Bundle bundle = (Bundle) e.viewData;
				String idBook = bundle.getString("idBook");
				String query = ConstantsDatabase.queryAllTable
						+ ConstantsDatabase.MENU_TABLE + " where idBook='"
						+ idBook + "' ;";
				Cursor result = DatabaseHelp.getInstance(base).selectQuery(
						query);
				ArrayList<MenuContent> arrBook = getArrMenuOfContentBook(result);
				DatabaseHelp.getInstance(base).close();
				model.setModelData(arrBook);
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				UserController.getInstance().handleModelEvent(model);
			} catch (Exception ex) {
				model.setModelMessage(ex.getMessage());
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				UserController.getInstance().handleErrorModelEvent(model);
			}
			break;
		}
		// handle event get data list menu and content of book
		case ActionEventConstant.GET_DATA_SEARCH_LIST_BOOK: {
			try {
				Bundle bundle = (Bundle) e.viewData;
				String idCatagory = bundle.getString("textSearch");
				String query = ConstantsDatabase.queryAllTable
						+ ConstantsDatabase.BOOK_TABLE + " where title like '%"
						+ idCatagory + "%' ;";
				Cursor result = DatabaseHelp.getInstance(base).selectQuery(
						query);
				ArrayList<Book> arrBook = getArrBook(result);
				DatabaseHelp.getInstance(base).close();
				model.setModelData(arrBook);
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				UserController.getInstance().handleModelEvent(model);
			} catch (Exception ex) {
				model.setModelMessage(ex.getMessage());
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				UserController.getInstance().handleErrorModelEvent(model);
			}
			break;
		}
		default:
			break;
		}

	}

	/**
	 * parse du lieu catagory
	 * 
	 * @author: truonglt2
	 * @param cursor
	 * @return
	 * @return: ArrayList<Catagory>
	 * @throws:
	 */
	private ArrayList<Catagory> getArrCatagory(Cursor cursor) {
		ArrayList<Catagory> arrayList = new ArrayList<Catagory>();
		if (cursor != null) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				Catagory item = new Catagory();
				String id = cursor.getString(0);
				String title = cursor.getString(1);
				item.setIdCatagory(id);
				item.setTitle(title);
				arrayList.add(item);
				cursor.moveToNext();
			}
			cursor.close();
		} else {
			return null;
		}
		return arrayList;
	}

	/**
	 * parse du lieu book
	 * 
	 * @author: truonglt2
	 * @param cursor
	 * @return
	 * @return: ArrayList<Book>
	 * @throws:
	 */
	private ArrayList<Book> getArrBook(Cursor cursor) {
		ArrayList<Book> arrayList = new ArrayList<Book>();
		if (cursor != null) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				Book item = new Book();
				// "author" TEXT,"idCatagory" INTEGER,"rate" FLOAT)
				String idBook = cursor.getString(0);
				String title = cursor.getString(1);
				String description = cursor.getString(2);
				String urlImage = cursor.getString(3);
				String price = cursor.getString(4);
				String author = cursor.getString(5);
				String idCatagory = cursor.getString(6);
				String rate = cursor.getString(7);

				item.setIdBook(idBook);
				item.setTitle(title);
				item.setDescription(description);
				item.setUrlImage(urlImage);
				item.setPrice(price);
				item.setAuthor(author);
				item.setIdCatagory(idCatagory);
				item.setRate(rate);
				arrayList.add(item);
				cursor.moveToNext();
			}
			cursor.close();
		} else {
			return null;
		}
		return arrayList;
	}

	/**
	 * parse du lieu menu
	 * 
	 * @author: truonglt2
	 * @param cursor
	 * @return
	 * @return: ArrayList<MenuContent>
	 * @throws:
	 */
	private ArrayList<MenuContent> getArrMenuOfContentBook(Cursor cursor) {
		ArrayList<MenuContent> arrayList = new ArrayList<MenuContent>();
		if (cursor != null) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				MenuContent item = new MenuContent();
				// ("idMenu" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,
				// "title" TEXT NOT NULL , "index" INTEGER, "level" INTEGER,
				// "content" TEXT, "idBook" INTEGER)
				String idMenu = cursor.getString(0);
				String title = cursor.getString(1);
				String index = cursor.getString(2);
				String level = cursor.getString(3);
				String content = cursor.getString(4);
				String idBook = cursor.getString(5);

				item.setTitle(title);

				arrayList.add(item);
				cursor.moveToNext();
			}
			cursor.close();
		} else {
			return null;
		}
		return arrayList;
	}
}
