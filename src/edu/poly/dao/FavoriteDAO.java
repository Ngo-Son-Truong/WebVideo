package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.common.JpaUtils;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.ReportUser;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

public class FavoriteDAO extends AbstractEntityDAO<Favorite> {

	public FavoriteDAO() {
		super(Favorite.class);
	}

	public List<ReportUser> reportUsersByVideo(String videoId) {
		String jpql = "select new edu.poly.domain.ReportUser(f.user.id, f.user.fullname, "
				+ "f.user.email, f.likeDate) from Favorite f where f.video.id = :videoId";

		EntityManager em = JpaUtils.getEntityManager();
		List<ReportUser> list = null;
		try {
			TypedQuery<ReportUser> query = em.createQuery(jpql, ReportUser.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	public List<FavoriteReport> reportFavoritesByVideos() {
		String jpql = "select new edu.poly.domain.FavoriteReport(f.video.title, count(f), min(f.likeDate), max(f.likeDate)) "
				+ " from Favorite f group by f.video.title ";
		EntityManager em = JpaUtils.getEntityManager();
		List<FavoriteReport> list = null;
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	EntityManager em = JpaUtils.getEntityManager();

	public List<Favorite> findAllVideoIdByUserId(int id) {
		em.clear();
		try {
			List<Favorite> list = em.find(User.class, id).getFavorites();
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Video> findAllByUserId(String id) {
		em.clear();
		try {
			String sql = "SELECT f.video FROM Favorite f WHERE f.user.id = :id";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			query.setParameter("id", id);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public Favorite findOneByUserIdAndVideoId(String userId, String videoId) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String sql = "SELECT f FROM Favorite f WHERE f.user.id = :userId and f.video.id = :videoId";
			TypedQuery<Favorite> query = em.createQuery(sql, Favorite.class);
			query.setParameter("userId", userId);
			query.setParameter("videoId", videoId);
			Favorite favorite = query.getSingleResult();
			return favorite;
		} catch (Exception e) {
			return null;
		}
	}
}
