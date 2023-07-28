package edu.poly.site;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class detail
 */
@WebServlet({ "/detail", "/detail/like", "/detail/unlike", "/detail/share" })
public class DetailVideo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	User user = new User();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		String videoId = request.getParameter("videoId");
		VideoDAO dao = new VideoDAO();
		Video video = dao.findById(videoId);
		if (username != null) {
			FavoriteDAO favdao = new FavoriteDAO();
			Favorite fav = favdao.findOneByUserIdAndVideoId(username, videoId);
			request.setAttribute("favorite", fav);
			request.setAttribute("username", username);
		} else {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		request.setAttribute("video", video);

		VideoDAO vdao = new VideoDAO();
		request.setAttribute("videos", vdao.random10Video());
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_VIDEO_DETAIL_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("like")) {
			this.like(request, response);
		} else if (uri.contains("unlike")) {
			this.unlike(request, response);
		} else if (uri.contains("share")) {
			this.share(request, response);
		}
	}

	private void share(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String videoId = request.getParameter("videoId");
		response.sendRedirect("/sharevideo?videoId=" + videoId);
	}

	private void unlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = SessionUtils.getLoginedUsername(request);
		String videoId = request.getParameter("videoId");
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			FavoriteDAO dao = new FavoriteDAO();
			Favorite favorite = dao.findOneByUserIdAndVideoId(userId, video.getId());
			dao.delete(favorite.getId());
			response.sendRedirect("/detail?videoId=" + videoId + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void like(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FavoriteDAO dao = new FavoriteDAO();
		String userId = SessionUtils.getLoginedUsername(request);
		Video video = new Video();
		String videoId = request.getParameter("videoId");
		if (userId != null) {
			try {
				Favorite favorite = new Favorite();
				BeanUtils.populate(video, request.getParameterMap());
				favorite.setVideo(video);
				User user = new User();
				user.setId(userId);
				favorite.setUser(user);
				favorite.setLikeDate(new Date());
				dao.insert(favorite);
				response.sendRedirect("/detail?videoId=" + videoId + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isCheck(User user) {
		if (user.getAdmin()) {
			return true;
		}
		return false;
	}
}
