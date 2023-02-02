package org.encheres.eni.dal;

import org.encheres.eni.BusinessException;

public interface DAO<T> {

	public void insert(T object) throws BusinessException;
	public T selectById(int Id);
	public void update(T object);
	public void delete(int id);
}