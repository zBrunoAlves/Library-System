package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CustomerDao;
import model.entities.Customer;

public class CustomerDaoJDBC implements CustomerDao {

	public static Connection conn = null;

	public CustomerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Customer obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Customer obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Customer obj = new Customer();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setCpf(rs.getString("cpf"));
				obj.setPhone(rs.getString("phone"));
				obj.setAndress(rs.getString("anddress"));

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
	public List<Customer> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM library.customer");
			rs = st.executeQuery();

			List<Customer> list = new ArrayList<Customer>();

			while (rs.next()) {
				Customer obj = new Customer();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setCpf(rs.getString("cpf"));
				obj.setPhone(rs.getString("phone"));
				obj.setAndress(rs.getString("anddress"));

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
