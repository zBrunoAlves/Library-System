package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SaleDao;
import model.entities.Customer;
import model.entities.Sale;

public class SaleDaoJDBC implements SaleDao {

	public static Connection conn;

	public SaleDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Sale obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Sale obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sale findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT sale.*, customer.name as cliente FROM sale INNER join customer on sale.customer_id = customer.id WHERE sale.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Sale obj = new Sale();

				Customer customer = new Customer();

				customer.setName(rs.getString("cliente"));
				obj.setId(rs.getInt("id"));
				obj.setSaleDate(rs.getDate("sale_date").toLocalDate());
				obj.setTotalPrice(rs.getDouble("total_price"));
				obj.setCustomer(customer);
				
				
				return obj;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Sale> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
