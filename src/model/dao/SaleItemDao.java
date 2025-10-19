package model.dao;

import java.util.List;

import model.entities.SaleItem;

public interface SaleItemDao {

	void insert(SaleItem obj);
	void update(SaleItem obj);
	void deleteById(Integer id);
	SaleItem findById(Integer id);
	List<SaleItem> findAll();
}
