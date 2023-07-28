package edu.poly.dao;

import java.util.List;
import javax.persistence.*;

import edu.poly.common.JpaUtils;
import edu.poly.model.Video;

public class VideoDAO extends AbstractEntityDAO<Video> {
	EntityManager em = JpaUtils.getEntityManager();

	public VideoDAO() {
		super(Video.class);
	}

	public List<Video> findAllOrderByViewDesc() {
		em.clear();
		try {
			String sql = "SELECT v FROM Video v ORDER BY v.views DESC";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Video> FindAllVideoView(int page, int pageSize) {
		try {
			EntityManager em = JpaUtils.getEntityManager();
			TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public List<Video> findOffsetVideo() {
		em.clear();
		try {
			String sql = "select v from Video v order by v.id OFFSET 6 ROWS";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public int getNumberPage() {
		Long total = this.countPage();
		int pageNumber = (int) (total / 12);
		if (total % 12 != 0) {
			pageNumber++;
		}
		return pageNumber;
	}

	public Long countPage() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String sql = "SELECT count(v) FROM Video v";
			TypedQuery<Long> query = em.createQuery(sql, Long.class);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}

	public List<Video> random10Video() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.random10", Video.class);
		return query.getResultList();
	}
}
