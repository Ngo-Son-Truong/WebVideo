package edu.poly.site;

import java.io.IOException;
import java.util.List;

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
import edu.poly.model.Favorite;
import edu.poly.model.Video;

/**
 * Servlet implementation class MyFavoriteServlet
 */
@WebServlet({ "/myfavorite", "/myfavorite/unlike", "/myfavorite/share" })
public class MyFavorite extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		FavoriteDAO dao = new FavoriteDAO();
		if (username != null) {
			List<Video> list = dao.findAllByUserId(username);
			if (list.size() > 0) {
				request.setAttribute("listVideo", list);
				request.setAttribute("username", username);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FAVORITE_PAGE);

			} else {
				request.setAttribute("message", "You don't have any favorite videos yet");
				request.setAttribute("username", username);
				request.getRequestDispatcher("/sites/common/404.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		if (url.contains("unlike")) {
			this.unlike(request, response);
		} else if (url.contains("share")) {
			this.share(request, response);
		}
	}

	private void unlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = SessionUtils.getLoginedUsername(request);
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			FavoriteDAO dao = new FavoriteDAO();
			Favorite favorite = dao.findOneByUserIdAndVideoId(userId, video.getId());
			dao.delete(favorite.getId());
			response.sendRedirect("/WebVideo/myfavorite");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void share(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String videoId = request.getParameter("videoId");
		response.sendRedirect("/WebVideo/sharevideo?videoId=" + videoId);
	}
}
