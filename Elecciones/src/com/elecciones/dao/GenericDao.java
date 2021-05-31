package com.elecciones.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

	public  void insert(T o) throws SQLException ;	
	public  void delete(int id) throws SQLException;
	public  void update(T o) throws SQLException;
	public <E> T select(int id);
	public List<T> selectAll();	
}
