package com.kunledarams.mobilewsapi.EntityManagerService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kunledarams.mobilewsapi.io.entity.UserEntity;

public class UserDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(UserEntity entity) {
		entityManager.persist(entity);
		return entity.getId();
	}
}
