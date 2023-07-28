package edu.poly.site;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.*;
import edu.poly.dao.ShareDAO;
import edu.poly.domain.Email;
import edu.poly.model.*;

/**
 * Servlet implementation class ShareVideoServlet
 */
@WebServlet("/sharevideo")
public class ShareVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("login");
			return;
		}
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			response.sendRedirect("/home");
			return;
		}
		request.setAttribute("videoId", videoId);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ShareDAO dao = new ShareDAO();
			Share share = new Share();
			Video video = new Video();
			User user = new User();
			String id = SessionUtils.getLoginedUsername(request);
			String emailForm = "duonghuyphi@gmail.com";
			String passFrom = "Phi26062002";
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			request.setAttribute("videoId", videoId);
			if (videoId == null) {
				request.setAttribute("error", "Video Id is null");
			} else {
				Email email = new Email();
				email.setFrom(emailForm);
				email.setFromPassword(passFrom);
				email.setTo(emailAddress);
				email.setSubject("Share Video | NGHIENPHIM");
				String msg = "Dear Ms/Mr. <br>The video is more interesting and I want to share with you.<br/>"
						+ "Vui lòng nhấp vào liên kết <a href='http://localhost:8080/WebVideo/detail?videoId=" + videoId
						+ "'> Xem video </a><br/>Regards<br/>Administrator";
				email.setContent(msg);
				EmailUtils.send(email);
				BeanUtils.populate(share, request.getParameterMap());
				share.setShareDate(new Date());
				user.setId(id);
				video.setId(videoId);
				// dao.insert(share);
				request.setAttribute("message", "Video link has been sent!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

}
