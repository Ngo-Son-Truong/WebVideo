package edu.poly.dao;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

import edu.poly.common.JpaUtils;

public abstract class AbstractEntityDAO<T> {
	private Class<T> entityClass;

	public AbstractEntityDAO(Class<T> cls) {
		this.entityClass = cls;
	}

	public void insert(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			T entity = em.find(entityClass, id);
			em.remove(entity);
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public T findById(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		T entity = em.find(entityClass, id);
		return entity;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			return em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long count(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<T> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(boolean all, int firstResult, int maxResult) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}
}
