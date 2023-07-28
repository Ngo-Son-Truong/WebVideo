package edu.poly.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.EmailUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.UserDAO;
import edu.poly.domain.Email;
import edu.poly.model.User;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/forgot")
public class ForgotPass extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserDAO dao = new UserDAO();
			String emailFrom = "duonghuyphi@gmail.com";
			String passForm = "Phi26062002";
			String emailTo = request.getParameter("email");
			String username = request.getParameter("username");
			User user = dao.findByUserEmail(username, emailTo);
			if (user == null) {
				request.setAttribute("error", "Username or email are incorrect");
			} else {
				Email email = new Email();
				email.setFrom(emailFrom);
				email.setFromPassword(passForm);
				email.setTo(emailTo);
				email.setSubject("Forgot Password | NGHIENPHIM");
				String msg = "Dear, " + username
						+ "<br/>You are used the forgot password function.<br/>Your password is <b>"
						+ user.getPassword() + "</b><br/>Regards<br/>Administrator";
				email.setContent(msg);
				EmailUtils.send(email);
				request.setAttribute("message", "Email has been sent! Please check and get your password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

}
