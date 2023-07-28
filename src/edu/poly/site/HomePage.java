package edu.poly.site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import edu.poly.common.*;
import edu.poly.dao.*;
import edu.poly.model.*;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/home")
public class HomePage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		try {
			VideoDAO dao = new VideoDAO();
			request.setAttribute("numberPage", dao.getNumberPage());
			String index = request.getParameter("page");
			if (index == null) {
				index = "1";
			}
			int indexPage = Integer.parseInt(index);
			request.setAttribute("indexPage", indexPage);
			List<Video> list = dao.FindAllVideoView((indexPage - 1) * 1, 12);
			FavoriteDAO favdao = new FavoriteDAO();
			List<Video> favlist = favdao.findAllByUserId(username);
			request.setAttribute("favlist", favlist);
			request.setAttribute("videos", list);
			request.setAttribute("username", username);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi hệ thống!");
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
