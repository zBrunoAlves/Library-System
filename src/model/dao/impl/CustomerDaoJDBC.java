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
import model.dao.CustomerDao;
import model.entities.Customer;

public class CustomerDaoJDBC implements CustomerDao {

	public Connection conn = null;

	public CustomerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Customer obj) {

		String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			st.setString(3, obj.getCpf());
			st.setString(3, obj.getPhone());
			st.setString(5, obj.getAndress());

			int linhasAlteradas = st.executeUpdate();

			System.out.println("Linhas alteradas: " + linhasAlteradas);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Customer obj) {

		String sql = "UPDATE customer SET name = ?, phone = ?, anddress = ? WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getName());
			st.setString(2, obj.getPhone());
			st.setString(3, obj.getAndress());
			st.setInt(4, obj.getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Erro ao atualizar linha");
			}

			System.out.println("Linhas atualizadas: " + linhasAfetadas);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void deleteById(Integer id) {

		String sql = "DELETE FROM customer WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			int rownsAffected = st.executeUpdate();

			System.out.println("Linhas executadas: " + rownsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}

	}

	@Override
	public Customer findById(Integer id) {

		String sql = "SELECT * FROM customer WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Customer obj = new Customer();
					obj.setId(rs.getInt("id"));
					obj.setName(rs.getString("name"));
					obj.setCpf(rs.getString("cpf"));
					obj.setPhone(rs.getString("phone"));
					obj.setAndress(rs.getString("anddress"));
					return obj;
				}
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		return null;
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
