package edu.poly.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

/**
 * Servlet implementation class VideoManagermentServlet
 */
@WebServlet({ "/admin/video", "/admin/video/create", "/admin/video/update", "/admin/video/delete", "/admin/video/reset",
		"/admin/video/edit" })
@MultipartConfig
public class VideoManagerment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		} else if (url.contains("delete")) {
			delete(request, response);
			return;
		} else if (url.contains("reset")) {
			reset(request, response);
			return;
		}
		Video video = new Video();
		video.setPoster("images/doraemon.png");
		findAll(request, response);
		request.setAttribute("video", video);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		if (url.contains("create")) {
			create(request, response);
			return;
		} else if (url.contains("delete")) {
			delete(request, response);
			return;
		} else if (url.contains("update")) {
			update(request, response);
			return;
		} else if (url.contains("reset")) {
			reset(request, response);
			return;
		}
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/desktop.jpg");
		request.setAttribute("video", video);
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());

			VideoDAO dao = new VideoDAO();
			Video oldVideo = dao.findById(video.getId());

			if (request.getPart("cover").getSize() == 0) {
				oldVideo.setPoster(video.getPoster());
			} else {
				oldVideo.setPoster(
						"uploads/" + UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster()));
			}
			oldVideo.setTitle(video.getTitle());
			oldVideo.setDescription(video.getDescription());
			oldVideo.setCode(video.getCode());
			oldVideo.setActive(video.getActive());
			oldVideo.setViews(video.getViews());
			dao.update(oldVideo);
			request.setAttribute("video", oldVideo);
			request.setAttribute("message", "Video has been updated!");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			video.setPoster(
					"uploads/" + UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster()));
			VideoDAO dao = new VideoDAO();
			dao.insert(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Video has been inserted!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			VideoDAO dao = new VideoDAO();
			List<Video> list = dao.findAll();
			request.setAttribute("videos", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");
		if (id == null) {
			request.setAttribute("error", "Video ID is required!");
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		try {
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(id);
			if (video == null) {
				request.setAttribute("error", "Video ID not found!");
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "Video has been deleted!");
			request.setAttribute("video", new Video());
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");
		if (id == null) {
			request.setAttribute("error", "Video ID is required!");
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		try {
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(id);
			request.setAttribute("video", video);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
}
