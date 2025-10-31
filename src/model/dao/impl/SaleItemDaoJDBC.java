package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.SaleItemDao;
import model.entities.Book;
import model.entities.Sale;
import model.entities.SaleItem;

public class SaleItemDaoJDBC implements SaleItemDao {

	private Connection conn = null;

	public SaleItemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(SaleItem obj) {

		String sql = "INSERT INTO sale_item (sale_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, obj.getSale().getId());
			st.setInt(2, obj.getBook().getId());
			st.setInt(3, obj.getQuantity());
			st.setDouble(4, obj.getPrice());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Nenhuma linha foi inserida!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(SaleItem obj) {

		String sql = "UPDATE sale_item SET sale_id = ?, book_id = ?, quantity = ?, price = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			
			st.setInt(1, obj.getSale().getId());
			st.setInt(2, obj.getBook().getId());
			st.setInt(3, obj.getQuantity());
			st.setDouble(4, obj.getPrice());
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM sale_item WHERE sale_id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, id);
			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Nenhum registro encontrado com o ID informado: " + id);
			}

			System.out.println("Linhas afetadas: " + linhasAfetadas);

		} catch (SQLException e) {
			throw new DbIntegrityException("Erro ao excluir registro: " + e.getMessage());
		}
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
		PreparedStatement st = null;
		ResultSet rs = null;

		List<SaleItem> list = new ArrayList<SaleItem>();

		try {
			st = conn.prepareStatement(
					"SELECT sale_item.* , book.title as title FROM sale_item INNER JOIN book on sale_item.book_id = book.id");

			rs = st.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));

				Sale sale = new Sale();
				sale.setId(rs.getInt("sale_id"));

				SaleItem obj = new SaleItem();
				obj.setBook(book);
				obj.setPrice(rs.getDouble("price"));
				obj.setQuantity(rs.getInt("quantity"));

				obj.setSale(sale);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
