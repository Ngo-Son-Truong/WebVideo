package edu.poly.site;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDAO;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class LikeVideoServlet
 */
@WebServlet("/like")
public class LikeVideo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("login");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			response.sendRedirect("/home");
			return;
		}
		try {
			FavoriteDAO dao = new FavoriteDAO();
			Favorite favorite = new Favorite();

			Video video = new Video();
			video.setId(videoId);
			favorite.setVideo(video);

			String username = SessionUtils.getLoginedUsername(request);
			User user = new User();
			user.setId(username);
			favorite.setUser(user);

			favorite.setLikeDate(new Date());
			dao.insert(favorite);
			request.setAttribute("message", "Video is added to Favorite");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		if (page == null) {
			page = "/home";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
