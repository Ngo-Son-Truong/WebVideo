package edu.poly.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = SessionUtils.getLoginedUsername(request);
		if (username == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(username);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			String username = SessionUtils.getLoginedUsername(request);
			UserDAO dao = new UserDAO();
			User oldUser = dao.findById(username);
			user.setAdmin(oldUser.getAdmin());
			dao.update(user);
			request.setAttribute("message", "User profile update!!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

}
