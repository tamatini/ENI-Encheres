package org.encheres.eni.dal;

import java.util.List;

import org.encheres.eni.BusinessException;

public interface DAO<T> {

	public void insert(T object) throws BusinessException;
	public T selectById(int Id) throws BusinessException;
	public void update(T object) throws BusinessException;
	public void delete(int id) throws BusinessException;
	List<T> selectAll() throws BusinessException;
}