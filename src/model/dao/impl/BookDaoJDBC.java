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
import model.dao.BookDao;
import model.entities.Book;

public class BookDaoJDBC implements BookDao {

	public Connection conn;

	public BookDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Book obj) {
	    String sql = "INSERT INTO book (title, author, price, release_year) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement st = conn.prepareStatement(sql)) {

	        st.setString(1, obj.getTitle());
	        st.setString(2, obj.getAuthor());
	        st.setDouble(3, obj.getPrice());
	        st.setInt(4, obj.getreleaseYear().getYear());

	        int linhasInseridas = st.executeUpdate();

	        if (linhasInseridas == 0) {
	            throw new DbException("Erro ao inserir os dados — nenhuma linha afetada.");
	        }

	        System.out.println("Livro inserido com sucesso!");

	    } catch (SQLException e) {
	        throw new DbException("Erro ao inserir os dados: " + e.getMessage());
	    }
	}


	@Override
	public void update(Book obj) {
		String sql = "UPDATE book SET title = ?, author = ?, price = ?, release_year = ? WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTitle());
			st.setString(2, obj.getAuthor());
			st.setDouble(3, obj.getPrice());
			st.setInt(4, obj.getreleaseYear().getYear());
			st.setInt(5, obj.getId());

			int linhasAlteradas = st.executeUpdate();

			if (linhasAlteradas == 0) {
				throw new DbException("Nenhum registro encontrado com o ID informado: " + obj.getId());
			}

			System.out.println("Linhas atualizadas: " + linhasAlteradas);

		} catch (SQLException e) {
			throw new DbException("Erro ao atualizar informações: " + e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM book WHERE id = ?");
			st.setInt(1, id);

			int rownsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rownsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Book findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM library.book WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Book obj = new Book();
				obj.setId(rs.getInt("id"));
				obj.setTitle(rs.getString("title"));
				obj.setAuthor(rs.getString("author"));
				obj.setreleaseYear(rs.getDate("release_year").toLocalDate());
				obj.setPrice(rs.getDouble("price"));

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
	public List<Book> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM library.book");
			rs = st.executeQuery();

			List<Book> list = new ArrayList<Book>();

			while (rs.next()) {
				Book obj = new Book();
				obj.setId(rs.getInt("id"));
				obj.setTitle(rs.getString("title"));
				obj.setAuthor(rs.getString("author"));
				obj.setreleaseYear(rs.getDate("release_year").toLocalDate());
				obj.setPrice(rs.getDouble("price"));

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
