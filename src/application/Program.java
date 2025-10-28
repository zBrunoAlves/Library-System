package application;

import model.dao.BookDao;
import model.dao.CustomerDao;
import model.dao.DaoFactory;
import model.dao.SaleDao;
import model.dao.SaleItemDao;

public class Program {

	public static void main(String[] args) {

		SaleItemDao saleItem = DaoFactory.createSaleItemDao();

		SaleDao sale = DaoFactory.createSaleDao();

		CustomerDao customer = DaoFactory.createCustomerDao();

		BookDao book = DaoFactory.createBookDao();

		System.out.println(book.findAll());
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println(customer.findAll());
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println(sale.findAll());
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println(saleItem.findAll());
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		customer.deleteById(4);
		customer.deleteById(3);
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("======================");
		saleItem.deleteById(1);

	}

}
