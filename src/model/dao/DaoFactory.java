package model.dao;

import db.DB;
import model.dao.impl.BookDaoJDBC;
import model.dao.impl.CustomerDaoJDBC;
import model.dao.impl.SaleDaoJDBC;
import model.dao.impl.SaleItemDaoJDBC;

public class DaoFactory {

	public static BookDao createBookDao() {
		return new BookDaoJDBC(DB.getConnection());
	}

	public static CustomerDao createCustomerDao() {
		return new CustomerDaoJDBC(DB.getConnection());
	}

	public static SaleDao createSaleDao() {
		return new SaleDaoJDBC(DB.getConnection());
	}

	public static SaleItemDao createSaleItemDao() {
		return new SaleItemDaoJDBC();
	}

}
