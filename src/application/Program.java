package application;

import model.dao.DaoFactory;
import model.dao.SaleItemDao;

public class Program {

	public static void main(String[] args) {

		SaleItemDao saleItem = DaoFactory.createSaleItemDao();
		
		System.out.println(saleItem.findById(1));
		
	}

}
