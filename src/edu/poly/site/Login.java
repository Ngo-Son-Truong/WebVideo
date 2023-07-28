package edu.poly.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.domain.LoginForm;
import edu.poly.model.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = CookieUtils.get("username", request);
		if (username != null) {
			SessionUtils.add(request, "username", username);
			//response.sendRedirect("home");
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
			return;
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, request.getParameterMap());
			UserDAO dao = new UserDAO();
			User user = dao.findById(form.getUsername());
			if (user != null && user.getPassword().equals(form.getPassword())) {
				SessionUtils.add(request, "username", user.getId());
				if (form.isRemember()) {
					CookieUtils.add("username", form.getUsername(), 0, response);
				} else {
					CookieUtils.add("username", form.getUsername(), 24, response);
				}
				if (this.isCheck(user)) {
					response.sendRedirect("admin/home");
				} else {
					response.sendRedirect("home");
				}
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());
				return;
			}
			request.setAttribute("error", "Invalid username or password");
		} catch (Exception e) {
			request.setAttribute("message", "Login failed!");
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

	public boolean isCheck(User user) {
		if (user.getAdmin()) {
			return true;
		}
		return false;
	}

}
