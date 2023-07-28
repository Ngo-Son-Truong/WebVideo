package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.common.JpaUtils;
import edu.poly.domain.ReportUserShare;
import edu.poly.model.Share;

public class ShareDAO extends AbstractEntityDAO<Share> {

	public ShareDAO() {
		super(Share.class);
	}

	public List<ReportUserShare> reportSharesVideo(String videoId) {
		String jpql = "select new edu.poly.domain.ReportUserShare(s.user.fullname, s.user.email, s.emails, s.shareDate) from Share s where s.video.id = :videoId";
		EntityManager em = JpaUtils.getEntityManager();
		List<ReportUserShare> list = null;
		try {
			TypedQuery<ReportUserShare> query = em.createQuery(jpql, ReportUserShare.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

}
