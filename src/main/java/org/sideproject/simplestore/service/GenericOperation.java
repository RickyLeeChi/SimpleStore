package org.sideproject.simplestore.service;

import org.springframework.data.repository.CrudRepository;

public class GenericOperation<T> implements Operation{
	
	private CrudRepository<T, Integer> repository;
	
	public CrudRepository<T, Integer> getRepository() {
		return repository;
	}
	
	public void setRepository(CrudRepository<T, Integer> repository) {
		this.repository = repository;
	}
	
	private T entity;
	private UserOP op;
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public UserOP getOp() {
		return op;
	}

	public void setOp(UserOP op) {
		this.op = op;
	}
	
	@Override
	public void execute() {
		
	}

}
