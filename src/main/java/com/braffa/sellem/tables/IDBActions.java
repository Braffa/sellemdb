package com.braffa.sellem.tables;

import java.sql.SQLException;
import java.util.List;

public interface IDBActions {
	

	public void createTable() throws SQLException;
	
	public abstract void create(Object o) throws SQLException ;

	public void delete(Object O) throws SQLException;
	
	public void dropTable() throws SQLException ;

	public void update(Object o) throws SQLException;
	
	public abstract List<?> read(Object key) throws SQLException;
	
	public abstract List<?> readByListOfKeys(String keys) throws SQLException;
	
	public abstract List<?> readAll() throws SQLException;

}
