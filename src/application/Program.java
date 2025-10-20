package application;

import model.dao.DaoFactory;
import model.dao.SaleDao;

public class Program {

	public static void main(String[] args) {

		SaleDao sale = DaoFactory.createSaleDao();
		
		System.out.println(sale.findById(1));
		
	}

}
