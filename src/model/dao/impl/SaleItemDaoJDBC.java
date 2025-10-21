package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SaleItemDao;
import model.entities.Book;
import model.entities.Sale;
import model.entities.SaleItem;

public class SaleItemDaoJDBC implements SaleItemDao {

	private static Connection conn = null;

	public SaleItemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(SaleItem obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SaleItem obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SaleItem findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT sale_item. *, book.title AS book FROM sale_item INNER JOIN book ON sale_item.book_id = book.id WHERE sale_item.sale_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				SaleItem obj = new SaleItem();
				obj.setPrice(rs.getDouble("price"));
				obj.setQuantity(rs.getInt("quantity"));

				Book book = new Book();
				book.setTitle(rs.getString("book"));

				Sale sale = new Sale();
				sale.setId(rs.getInt("sale_id"));

				obj.setBook(book);
				obj.setSale(sale);

				return obj;

			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<SaleItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
