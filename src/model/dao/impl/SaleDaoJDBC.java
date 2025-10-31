package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SaleDao;
import model.entities.Customer;
import model.entities.Sale;

public class SaleDaoJDBC implements SaleDao {

	public Connection conn;

	public SaleDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Sale obj) {

		String sql = "INSERT INTO sale (sale_date, customer_id, total_price) VALUES (?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

			st.setDate(1, java.sql.Date.valueOf(obj.getSaleDate()));
			st.setInt(2, obj.getCustomer().getId());
			st.setDouble(3, obj.getTotalPrice());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}
				}
			} else {
				throw new DbException("Nenhuma linha foi inserida!");
			}

		} catch (SQLException e) {
			throw new DbException("Erro ao inserir valores: " + e.getMessage());
		}

	}

	@Override
	public void update(Sale obj) {

		String sql = "UPDATE sale SET sale_date = ?, customer_id = ?, total_price = ? WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setDate(1, java.sql.Date.valueOf(obj.getSaleDate()));
			st.setInt(2, obj.getCustomer().getId());
			st.setDouble(3, obj.getTotalPrice());
			st.setInt(4, obj.getId());

			int rowsAffected = st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM sale WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, id);
			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Nenhum registro encontrado com o ID informado: " + id);
			}

			System.out.println("Linhas afetadas: " + linhasAfetadas);
		} catch (SQLException e) {
			throw new DbException("Erro ao excluir registro: " + e.getMessage());
		}
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
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Sale> list = new ArrayList<Sale>();

		try {
			st = conn.prepareStatement(
					"SELECT sale.* , customer.name as cliente FROM sale INNER join customer on sale.customer_id = customer.id");
			rs = st.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setName(rs.getString("cliente"));
				Sale obj = new Sale();
				obj.setCustomer(customer);
				obj.setId(rs.getInt("id"));
				obj.setSaleDate(rs.getDate("sale_date").toLocalDate());
				obj.setTotalPrice(rs.getDouble("total_price"));

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
