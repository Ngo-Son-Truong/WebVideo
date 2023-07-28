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
import edu.poly.domain.ChangePassF;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change-password")
public class ChangePass extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		if (username == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		request.setAttribute("username", username);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = SessionUtils.getLoginedUsername(request);
			ChangePassF form = new ChangePassF();
			BeanUtils.populate(form, request.getParameterMap());

			String currentpassword = request.getParameter("currentPassword");
			form.setCurrentPassowrd(currentpassword);
			request.setAttribute("username", username);
			if (!form.getConfirmPasswrod().equals(form.getPassword())) {
				request.setAttribute("error", "New password and new confirm password are not identical.");
			} else {
				UserDAO dao = new UserDAO();
				dao.changePassword(form.getUsername(), form.getCurrentPassowrd(), form.getPassword());
				request.setAttribute("message", "Password has been changed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

}
